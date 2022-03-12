对于不同 GC 和堆内存的总结

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

配置堆内存 512M，YGC 后年轻代存活对象大概 20M。

- **串行 GC** 利用单线程执行，GC 暂停的时间明显会比较长。在实际的测试下，在小堆内存空间的情况下，YGC 和并行 GC 的 YGC 差不多。FGC 使用的时间明显较长，**大概是并行 GC 的一倍（存活对象 300M 左右）**
  。老年代存活对象占用的空间大，整理移动的时间就长。
- CMS GC 的老年代清理明显的暂停时间降低。在 GC 日志中有发现 concurrent mode failure 的情况。查询资料后明白，CMS 在 cleanup
  是并发执行的，这时的对象引用关系发生改变，也可能有新的对象需要分配空间。如果没有预留足够的空间内存分配就会导致并发失败。可能重新 CMS ，或者 GC 退化成 Serial。
- G1 GC 出现了 Humongous Allocation 因为大对象分配失败，触发了 initial-mark。也是重新标记，或者 GC 退化的问题。

[参考](https://xie.infoq.cn/article/b0cb6610d5f96832af321ec98)

