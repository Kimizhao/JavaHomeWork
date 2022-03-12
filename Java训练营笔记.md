# Java训练营笔记

## Week1



生成字节码

```
javac code.java
```







## Week2

GC日志解读与分析



生成字节码

```bash
javac GCLogAnalysis.java
```



直接运行

```bash
$ java GCLogAnalysis
正在执行...
执行结束!共生成对象次数:20560
```



带打印参数

```bash
$ java -XX:+PrintGCDetails -Xmx1g -Xms1g GCLogAnalysis
```



带打印参数，时间戳

```bash
$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g GCLogAnalysis
```



输出到日志文件

```bash
$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo.log -Xmx1g -Xms1g GCLogAnalysis
```



java 8 默认不配置GC策略的话，是并行GC



串行GC

```bash
$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis
```



并行GC

```bash
$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseParallelGC GCLogAnalysis
```



CMS GC 针对OLD区

```bash
$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis
```



G1 GC

```bash
$ java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseG1GC GCLogAnalysis

# 查看概要信息
$ java -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseG1GC GCLogAnalysis
```



Java 实现一个最简单的HTTP服务器-01

HttpServer01

压测命令

```shell
$ sb -u http://localhost:8801 -c 40 -N 30
```



Netty例子

常用4.x版本，5.x已弃用

4.1.51.Final



Netty实现HttpServer

[localhost:8808/test](http://localhost:8808/test)

[localhost:8808](http://localhost:8808/)



压测

```powershell
$ sb -u http://localhost:8808/test -c 40 -N 30
```



依赖， 推荐 mvnrepository: https://mvnrepository.com/



作业参考

地址：https://github.com/renfufei/JAVA-000



### 作业

1. （选做）使用 [GCLogAnalysis.java](./GCLogAnalysis.java) 自己演练一遍 串行/并行/CMS/G1 的案例。


2. （选做）使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。
3. （选做）如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。
4. （必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。
5. （选做）运行课上的例子，以及 Netty 的例子，分析相关现象。
6. （必做）写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub