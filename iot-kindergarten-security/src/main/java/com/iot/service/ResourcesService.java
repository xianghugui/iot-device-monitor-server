package com.iot.service;

import com.iot.entity.Resources;
import com.iot.entity.hsweb.DateTimeUtils;
import com.iot.entity.hsweb.MD5;
import com.iot.entity.hsweb.StringUtils;
import com.iot.mapper.ResourcesMapper;
import com.iot.security.common.biz.BaseBiz;
import com.iot.security.common.msg.ObjectRestResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 资源服务类
 * Created by generator
 */
@Service
public class ResourcesService extends BaseBiz<ResourcesMapper, Resources> {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    //默认数据映射接口
    @Resource
    protected ResourcesMapper resourcesMapper;

    @Value("${multipart.location}")
    private String FILE_BASE_PATH;

    //文件名中不允许出现的字符 \ / : | ? < > "
    private static final Pattern fileNameKeyWordPattern = Pattern.compile("(\\\\)|(/)|(:)(|)|(\\?)|(>)|(<)|(\")");

    private static final Map<String, String> mediaTypeMapper = new HashMap<>();

    static {
        mediaTypeMapper.put("png", MediaType.IMAGE_PNG_VALUE);
        mediaTypeMapper.put("jpg", MediaType.IMAGE_JPEG_VALUE);
        mediaTypeMapper.put("jpeg", MediaType.IMAGE_JPEG_VALUE);
        mediaTypeMapper.put("gif", MediaType.IMAGE_GIF_VALUE);
        mediaTypeMapper.put("bmp", MediaType.IMAGE_JPEG_VALUE);
        mediaTypeMapper.put("json", MediaType.APPLICATION_JSON_VALUE);
        mediaTypeMapper.put("txt", MediaType.TEXT_PLAIN_VALUE);
        mediaTypeMapper.put("css", MediaType.TEXT_PLAIN_VALUE);
        mediaTypeMapper.put("js", "application/javascript");
        mediaTypeMapper.put("html", MediaType.TEXT_HTML_VALUE);
        mediaTypeMapper.put("xml", MediaType.TEXT_XML_VALUE);
        mediaTypeMapper.put("mp4", "video/mp4");
    }

    public void deleteResources(Long resourceId) throws NotFoundException {
        Resources resources = super.selectById(resourceId);
        if (resources == null) throw new NotFoundException("文件不存在");
        deleteResources(resources);
        resourcesMapper.delete(resources);
    }

    public InputStream readResources(Resources resources) throws IOException, NotFoundException {
        File file = new File(FILE_BASE_PATH.concat(resources.getPath().concat("/".concat(resources.getMd5()))));
        if (!file.canRead()) {
            throw new NotFoundException("文件不存在");
        }
        return new FileInputStream(file);
    }

    public void deleteResources(Resources resources) {
        File file = new File(FILE_BASE_PATH.concat(resources.getPath().concat("/".concat(resources.getMd5()))));
        if (file.exists()) {
            file.delete();
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public Resources saveFile(InputStream is, String fileName) throws IOException {
        //文件存储的相对路径，以日期分隔，每天创建一个新的目录
        String filePath = "/file/".concat(DateTimeUtils.format(new Date(), DateTimeUtils.YEAR_MONTH_DAY));
        //文件存储绝对路径
        String absPath = FILE_BASE_PATH.concat(filePath);
        File path = new File(absPath);
        if (!path.exists()) path.mkdirs(); //创建目录
        String newName = MD5.encode(String.valueOf(System.nanoTime())); //临时文件名 ,纳秒的md5值
        String fileAbsName = absPath.concat("/").concat(newName);
        //try with resource
        long fileLength = 0;
        fileLength = getFileLength(is, fileAbsName, fileLength);
        File newFile = new File(fileAbsName);
        //获取文件的md5值
        String md5;
        try (FileInputStream inputStream = new FileInputStream(newFile)) {
            md5 = DigestUtils.md5Hex(inputStream);
        }
        //判断文件是否已经存在
        Resources resources = resourcesMapper.selectByMd5(md5);
        if (resources != null) {
            newFile.delete();//文件已存在则删除临时文件不做处理
            return resources;
        } else {
            newName = md5;
            newFile.renameTo(new File(absPath.concat("/").concat(newName)));
        }
        resources = new Resources();
        resources.setId(System.nanoTime());
        resources.setPath(filePath);
        resources.setMd5(md5);
        resources.setType("file");
        resources.setSize(fileLength);
        resources.setName(fileName);
        resources.setCreateTime(new Date());
        super.insertSelective(resources);
        return resources;
    }

    /**
     * 获取上传资源长度
     *
     * @param is
     * @param fileAbsName
     * @param fileLength
     * @return
     */
    public long getFileLength(InputStream is, String fileAbsName, long fileLength) {
        try (BufferedInputStream in = new BufferedInputStream(is);
             BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(fileAbsName))) {
            byte[] buffer = new byte[2048 * 10];
            int len;
            while ((len = in.read(buffer)) != -1) {
                fileLength += len;
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLength;
    }


    /**
     * 上传文件,支持多文件上传.获取到文件流后,调用{@link saveFile(InputStream, String)}进行文件保存
     * 上传成功后,将返回资源信息如:[{"id":"fileId","name":"fileName","md5":"md5"}]
     *
     * @param files 文件列表
     * @return 文件上传结果.
     * @throws IOException 保存文件错误
     */
    public List<Resources> upload(MultipartFile[] files) throws IOException {
        if (logger.isInfoEnabled())
            logger.info(String.format("start upload , file number:%s", files.length));
        List<Resources> resourcesList = new LinkedList<>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                if (logger.isInfoEnabled())
                    logger.info("start write file:{}", file.getOriginalFilename());
                String fileName = file.getOriginalFilename();
                Resources resources = saveFile(file.getInputStream(), fileName);
                resourcesList.add(resources);
            }
        }//响应上传成功的资源信息

        return resourcesList;
    }

    /**
     * 通过文件ID下载已经上传的文件,支持断点下载
     * 如: http://host:port/file/download/aSk2a/file.zip 将下载 ID为aSk2a的文件.并命名为file.zip
     *
     * @param id       要下载资源文件的id
     * @param name     自定义文件名，该文件名不能存在非法字符.如果此参数为空(null).将使用文件上传时的文件名
     * @param response {@link HttpServletResponse}
     * @param request  {@link HttpServletRequest}
     * @return 下载结果, 在下载失败时, 将返回错误信息
     * @throws IOException       读写文件错误
     * @throws NotFoundException 文件不存在
     */
    public ObjectRestResponse download(String id, String name,
                                       HttpServletResponse response,
                                       HttpServletRequest request) throws Exception {
        Resources resources = super.selectById(Long.valueOf(id));
        if (resources == null) {
            throw new NotFoundException(id + "文件不存在");
        } else {
            if (!"file".equals(resources.getType()))
                throw new NotFoundException("文件不存在");

            String contentType = mediaTypeMapper.get(resources.getSuffix().toLowerCase());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            //未自定义文件名，则使用上传时的文件名
            if (StringUtils.isNullOrEmpty(name))
                name = resources.getName();
            //如果未指定文件拓展名，则追加默认的文件拓展名
            if (!name.contains("."))
                name = name.concat(".").concat(resources.getSuffix());
            //关键字剔除
            name = fileNameKeyWordPattern.matcher(name).replaceAll("");
            int skip = 0;
            long fSize = resources.getSize();
            //尝试判断是否为断点下载
            try {
                //获取要继续下载的位置
                String Range = request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", "");
                skip = StringUtils.toInt(Range);
            } catch (Exception e) {
            }
            response.setContentLength((int) fSize);//文件大小
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
            //try with resource
            try (BufferedInputStream inputStream = new BufferedInputStream(readResources(resources));
                 BufferedOutputStream stream = new BufferedOutputStream(response.getOutputStream())) {
                //断点下载
                if (skip > 0) {
                    inputStream.skip(skip);
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    String contentRange = new StringBuffer("bytes ").append(skip).append("-").append(fSize - 1).append("/").append(fSize).toString();
                    response.setHeader("Content-Range", contentRange);
                }
                byte b[] = new byte[(int) fSize];
                while ((inputStream.read(b)) != -1) {
                    stream.write(b);
                }
                stream.flush();
            } catch (IOException e) {
                logger.debug(String.format("download file error%s", e.getMessage()));
                throw e;
            }
            return null;
        }
    }

}
