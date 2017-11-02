package com.horvan.network.network;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class NettyServer {
	
	static Logger logger = LoggerFactory.getLogger(NettyServer.class);
	
	public static void main(String[] args) {
		logger.info("开始初始化网络服务器了");
		ServerBootstrap serverBootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		logger.info("设置事件管道了");
		serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new StringDecoder(),new StringEncoder(),new ServerHandler());
			}
		});
		
		logger.info("绑定端口,并开始接收 客户端的连接");
		
		Channel bind = serverBootstrap.bind(new InetSocketAddress(8000));
		logger.info("server 已经启动，监听端口:"+ bind.getLocalAddress() +",等待客户端连接。。。。");
		
		
		
	}
	
	private static class ServerHandler extends SimpleChannelHandler {

		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.info("有一个客户端注册退出了。。。");
			super.channelClosed(ctx, e);
		}

		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.info("有一个客户端注册进来了。。。");
			logger.info("client"+e.getChannel().getRemoteAddress());
			logger.info("Server"+e.getChannel().getLocalAddress());
			super.channelConnected(ctx, e);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
			super.exceptionCaught(ctx, e);
		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
			if(e.getMessage() instanceof String){
				  	String message = (String) e.getMessage();  
	                logger.info("Client发来:" + message);  
	                e.getChannel().write("Server已收到刚发送的:" + message);  
	                logger.info("\n等待客户端输入。。。");  
			}
			super.messageReceived(ctx, e);
		}
		
		
	}
	
	
}
