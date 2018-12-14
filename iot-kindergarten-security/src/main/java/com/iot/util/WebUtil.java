package com.iot.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    /**
     * web应用绝对路径
     *
     * @param request 请求对象
     * @return 绝对路径
     */
    public static StringBuffer getBasePath(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
                .append(request.getServerPort()).append(request.getContextPath()).append("/");
        return sb;
    }

    /**
     * 拼接文件路径
     *
     * @param req
     * @param id
     * @return
     */
    public static String resourceBuildPath(HttpServletRequest req, String id) {
//        return getBasePath(req).append("resources/download/").append(id).toString();
        return new StringBuffer("https://zxjl.lmlm.cn/resources/download/").append(id).toString();
    }
}
