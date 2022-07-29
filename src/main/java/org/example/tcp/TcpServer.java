package org.example.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.example.model.RequestDataDecoder;
import org.example.model.ResponseDataEncoder;

/**
 * @author yuhangbin
 * @date 2022/7/24
 **/
public class TcpServer {

	public static String host = "localhost";
	public static int port = 8000;

	public static void main(String[] args) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(10);
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new RequestDataDecoder(), new ResponseDataEncoder(), new ServerHandler());
				}
			});
			ChannelFuture future = bootstrap.bind(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
