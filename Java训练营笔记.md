# Java训练营笔记

## Week1



生成字节码

```
javac code.java
```







## Week2

### GC日志解读与分析



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



### Java 实现一个最简单的HTTP服务器-01

HttpServer01

压测命令

```shell
$ sb -u http://localhost:8801 -c 40 -N 30
```



### Netty例子

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



## Week3

TPS 事务数

QPS 查询数



从Socket IO 到 NIO

从 Socket IO 到 NIO--BIO多线程模型

从事件处理机制到 Reactor 模型

反应堆模型

从 Reactor 模型到 Netty NIO --01

Dung Lea 《Scalable IO in Java》



### 2.Netty 如何实现高性能

**Netty对三种模式的支持**

Reactor单线程模式、非主从Reactor多线程模式、主从Reactor多线程模式



**关键对象**

Bootstrap: 启动线程，开启 socket 

EventLoopGroup EventLoop 

SocketChannel: 连接 

ChannelInitializer: 初始化 

ChannelPipeline: 处理器链 

ChannelHandler: 处理器



### 3.Netty 网络程序优化

00:06 粘包和拆包

卡车运送快递



08:10 Nagle 与 TCP_NODELAY

MTU: Maxitum Transmission Unit 最 大传输单元 

MSS: Maxitum Segment Size 最大分 段大小, 为 MTU - 20(IP) - 20(TCP)

MTU = 1500 Byte

MSS = 1446 Byte



15:08 连接优化

用**三次握手**建立TCP连接的各状态

用**四次挥手**关闭连接

TCP连接必须经过时间2MSL后才真正释放掉

Linux上MSL默认1分钟

Windows上默认为2分钟

压测时候注意这个时间，压测完一次后，等一段时间后在进行压测



23:48 Netty 优化

1、不要阻塞 EventLoop 

2、系统参数优化 

Linux---ulimit -a, /proc/sys/net/ipv4/tcp_fin_timeout, 

Windows---TcpTimedWaitDelay 

3、缓冲区优化 

SO_RCVBUF/SO_SNDBUF/SO_BACKLOG/ REUSEXXX 

4、心跳周期优化 

心跳机制与短线重连 

5、内存与 ByteBuffer 优化 

DirectBuffer 与 HeapBuffer 

6、其他优化 

- ioRatio 
- Watermark
- TrafficShaping



### 4.典型应用：API 网关

#### 00:20 网关的结构和功能

请求接入--作为所有API接口服务请求的接入点

业务聚合--作为所有后端业务服务的聚合点

中介策略--实现安全、验证、路由、过滤、流控等策略

统一管理--对所有API服务和策略进行统一管理

#### 03:14 网关的分类

流量网关

关注稳定与安全 l 全局性流控 l 日志统计 l 防止 SQL 注入 l 防止 Web 攻击 l 屏蔽工具扫描 l 黑白 IP 名单 l 证书/加解密处理

业务网关

提供更好的服务 l 服务级别流控 l 服务降级与熔断 l 路由与负载均衡、灰度策略 l 服务过滤、聚合与发现 l 权限验证与用户等级策略 l 业务规则与参数校验 l 多级缓存策略



OpenResty-Nginx内核实现



#### 10:15 常见的几种 API 网关的实现

zuul

zuul2 netty重构版

Sprint Cloud Gateway--Sprint 5 webflux，netty基础



#### 14:50 网关对比

性能非常好，适合流量网关

OpenResty，通过Lua扩展NGINX实现的可伸缩的Web平台

Kong，进一步完善OpenResty，有控制台



扩展性好，适合业务网关，**二次开发**

Sprint Cloud Gateway

Zuul2



猫大人的soul

https://github.com/apache/incubator-shenyu/blob/master/README_CN.md



### 5.自己动手实现 API 网关

最简单的网关--gateway 1.0



最简单的网关--gateway 2.0

+过滤器



最简单的网关--gateway 3.0

+过滤器

+路由



### 6.多线程基础



Broos《人月神话》里说加人可能干得更慢

Java线程

Thread

run

start



### 7.

Thread 类

Runnable 接口

线程状态

- Runnable

- Running

- Non-Runnable

- Terminated



Thread状态



## Week4

### 1.Java并发包





https://shimo.im/docs/AS6dIl9n7e8fWO99/ 《Java训练营知识点430问》，可复制链接后用石墨文档 App 或小程序打开



### 2.锁



### 3.原子计数类





### 4.并发工具类



### 5.

ArrayList、LinkedList 均存在线程安全问题。

既然线程安全是写冲突和读写冲突导致的
最简单办法就是，读写都加锁。
例如：

- 1.ArrayList 的方法都加上 synchronized -> Vector
- 2.Collections.synchronizedList，强制将 List 的操作加上同步
- 3.Arrays.asList，不允许添加删除，但是可以 set 替换元素
- 4.Collections.unmodifiableList，不允许修改内容，包括添加删除和 set