# zzq

#### 介绍
自走棋阵容模拟器

#### 软件架构
spring boot+spring data jpa+spring data redis+spring cache


#### 安装教程

前端网页使用nginx部署，网页目录:resources/static/index.html
后台程序使用maven打包后执行jar包，可参考如下启动命令：

```
nohup java -Xmx200M -Xms50M -jar ./target/zzq-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > log.out 2>&1 & echo $! > PID
```

#### 使用说明

1. maven打包前请先将application-dev和application-prod中的db和redis连接配置项补全
