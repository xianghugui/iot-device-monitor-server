
--------------------------------------------------------------------------------
consul   cmd 命令窗口启动：

consul agent -dev

consul 自带 ui 界面， 打开网址：http://localhost:8500, 可以看到当前服务注册界面

--------------------------------------------------------------------------------
consul   cmd 命令窗口启动：

net stop RabbitMQ && net start RabbitMQ

使用浏览器打开 http://localhost:15672 访问Rabbit Mq的管理控制台，使用刚才创建的账号登陆系统

--------------------------------------------------------------------------------

下载完后端代码后，记得先安装lombok插件，否则你的IDE会报代码缺失。

运行步骤
先启动rabbitmq、redis、mysql以及consul注册中心

运行数据库脚本：依次运行数据库：
iot-modules/iot-admin/db/init.sql;
iot-auth/iot-auth-server/db/init.sql;
iot-control/iot-trace/db/init.sql

修改配置数据库配置：
iot-admin/src/main/resources/application.yml;
iot-gate/src/main/resources/application.yml
按顺序运行main类：
CenterBootstrap（iot-eureka-service）;
AuthBootstrap（iot-auth/iot-auth-server）;
AdminBootstrap（iot-modules/iot-admin）;
GatewayServerBootstrap（iot-gate/iot-gateway）

--------------------------------------------------------------------------------

## License
Apache License Version 2.0