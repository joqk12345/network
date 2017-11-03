# 基于netty的demo
1. 目的：快速开发高性能、高可靠性的网络服务器和客户端程序
2. 优点：提供异步的、事件驱动的网络应用程序框架和工具
3. 通俗的说：一个好使的处理Socket的东东
4. 如果没有Netty？
    1. 远古：java.net + java.io
    2. 近代：java.nio
    3. 其他：Mina，Grizzly
# netty特性
1. 设计
    1. 统一的API，适用于不同协议（阻塞、非阻塞）
    2. 基于灵活、可扩展的事件驱动模型
    3. 高度定制的线程模型
    4. 可靠无连接数据socket支持（udp)
2. 性能
    1. 更好吞吐量，低延迟
    2. 更省资源
    3. 尽量减少不必要的内容拷贝
3. 安全
    1. 完整的SSL/TLS和STARTTTLS的支持
    2. 能在Applet与Android的限制环境运行良好
4. 健壮性
    1. 不再因为过快、过慢活超负载连接导致OOM
    2. 不在有在告诉网络环境下NIO读写频率不一致的问题
5. 易用
    1. 晚上的javadoc 用户指南和阳历
    2. 简介简单
    3. 仅依赖于jdk1.5
# 核心组件
1. netty组件 
    1. ChannelFactory
    2. Boss
    3. Worker
    4. Channel
    5. ChannelEvent
    6. Pipeline
    7. Handler
    8. Sink
2. Server端核心类
    1. NioServerSocketChannelFactory
    2. NioServerBossPool
    3. NioWorkerPool
    4. NioServerBoss
    5. NioWorker
    6. NioServerSocketChannel
    7. NioAcceptedSocketChannel
    8. DefaultChannelPipeline
    9. NioServerSocketPipelineSink
    10.Channels
3. ChannelFactory -- channel工厂，很重要的类，保存启动相关参数
    1. NioServerSocketChannelFactory
    2. NioClientSocketChannelFactory
    3. NioDatagramChannelFactory
    4. 这是Nio的，还有Oio和Local的
4. SelectorPool--- selector的线程池
    1. NioServerBossPool  1
    2. NioClientBossPool  1    
    3. NioWorkerPool       2 * Processor
    4. NioDatagramWorkerPool
5. Selector -选择器，核心组件
    1. NioServerBoss
    2. NioClientBoss
    3. NioWorker
    4. NioDatagramWorker
6. Channel -- 通道
    1. NioServerSocketChannel
    2. NioClientSocketChannel
    3. NioAcceptedSocketChannel
    4. NioDatagramChannel
7. Sink --负责和底层的交互，如bind、write、close等
    1. NioServerSocketPipelineSink
    2. NioClientSocketPipelineSink
    3. NioDatagramPipelineSink
8. Pipeline --负责维护所有的Handler
9. ChannelContext 一个Channel一个是Handler和Pipeline的中间件
10. Handler -- 对Channel事件的处理器
11. channelPipeline
    1. ![channelPipeline](http://dl2.iteye.com/upload/attachment/0086/9698/a1ea7a7d-95b2-33f2-a494-0f4c31d8dfc3.png)
    2. ![channelPipeline](http://dl2.iteye.com/upload/attachment/0086/9700/32e34f3c-4842-3a0c-9b59-9fbc52c0b54c.jpg)
12. 线程模型
    1. ![线程模型](http://dl2.iteye.com/upload/attachment/0086/9702/82978f65-4826-311a-9275-3076d026b890.jpg)
    2. ![事件驱动](http://dl2.iteye.com/upload/attachment/0086/9704/acea5874-8886-3495-b19c-184925bc2506.gif)    
 
