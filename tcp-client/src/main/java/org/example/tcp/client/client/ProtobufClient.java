package org.example.tcp.client.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.example.protobuf.Hello;
import org.example.tcp.client.handler.ClientHandler;

/**
 * @author yuhangbin
 * @date 2022/8/28
 **/
public class ProtobufClient implements TcpClient{

	public static final int PORT = 10101;
//	public static final String HOSTNAME = "127.0.0.1";
	public static final String HOSTNAME = "101.43.99.81";
	public EventLoopGroup group;
	private Channel channel;

	@Override public void start() {
		try {
			doStart();
		}catch (Throwable e) {
			e.printStackTrace();
		}finally {
			if (group != null) {
				group.shutdownGracefully();
			}
		}
	}

	@Override
	public void stop() {
		try {
			channel.closeFuture().sync();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean send(Object msg) {
		ChannelFuture channelFuture = channel.writeAndFlush(msg);
		return channelFuture.isSuccess();
	}

	private void doStart() throws Exception{
		 group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					// inbound
					pipeline.addLast(new ProtobufVarint32FrameDecoder());
					pipeline.addLast(new ProtobufDecoder(Hello.getDefaultInstance()));
					pipeline.addLast(ClientHandler.ClientHandlerHolder.INSTANCE);
					// outbound
					pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
					pipeline.addLast(new ProtobufEncoder());
				}
			});
		channel = bootstrap.connect(HOSTNAME, PORT).sync().channel();
	}


}
