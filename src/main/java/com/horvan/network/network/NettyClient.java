package com.horvan.network.network;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class NettyClient {

	static Logger logger = LoggerFactory.getLogger(NettyClient.class);
	
	public static void main(String[] args) {
		
		logger.info("初始化客户端。。。。");
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		logger.info("设置默认的事件管道。。。。");
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new StringDecoder(),new StringEncoder(),new ClientHandler());
			}
		});
		
		logger.info("开始连接尝试..");
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8000));
		
		logger.info("等待知道连接关闭或者是尝试连接失败");
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		logger.info("关闭线城池，并且退出。");
		bootstrap.releaseExternalResources();
		
		
	}

	private static class ClientHandler extends SimpleChannelHandler{
		
		private BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));  
		
		@Override
		public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			// TODO Auto-generated method stub
			super.channelClosed(ctx, e);
		}

		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			logger.info("已经与Server建立连接。。。。");  
			logger.info("\n请输入要发送的信息：");  
			super.channelConnected(ctx, e);
			e.getChannel().write(sin.readLine());  
		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		     if (e.getMessage() instanceof String) {  
	                String message = (String) e.getMessage();  
	                logger.info(message);  
	                e.getChannel().write(sin.readLine());  
	                logger.info("\n等待客户端输入。。。");  
	            }  
			super.messageReceived(ctx, e);
		}
		
	}
}
