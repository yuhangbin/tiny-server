package org.example.tcp.server.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.example.protobuf.Hello;
import org.example.tcp.server.handler.ServerHandler;

/**
 * @author yuhangbin
 * @date 2022/8/14
 **/
public class ProtobufServer implements TcpServer{

	public static final int BOSS_THREAD_COUNT = 1;
	public static final int WORKER_THREAD_COUNT = 10;
	public static final int DEFAULT_PORT = 10101;

	public EventLoopGroup bossGroup;
	public EventLoopGroup workerGroup;

	@Override public void start() {
		try {
			doStart();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  void doStart() throws InterruptedException {
		bossGroup = new NioEventLoopGroup(BOSS_THREAD_COUNT);
		workerGroup = new NioEventLoopGroup(WORKER_THREAD_COUNT);
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline channelPipeline = ch.pipeline();
					channelPipeline
						// protobuf decode
						.addLast(new ProtobufVarint32FrameDecoder())
						.addLast(new ProtobufDecoder(Hello.getDefaultInstance()))
						// logic handler
						.addLast(ServerHandler.ServerHandlerHolder.INSTANCE)
						// protobuf encode
						.addLast(new ProtobufVarint32LengthFieldPrepender())
						.addLast(new ProtobufEncoder());
				}
			});
			ChannelFuture future = serverBootstrap.bind(DEFAULT_PORT).sync();

			future.channel().closeFuture().sync();
	}

	@Override public void shutdown() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}
