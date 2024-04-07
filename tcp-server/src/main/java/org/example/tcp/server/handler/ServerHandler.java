package org.example.tcp.server.handler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protobuf.Hello;

/**
 * @author yuhangbin
 * @date 2022/8/27
 **/
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<GeneratedMessageV3> {

	public static class ServerHandlerHolder{
		public static final ServerHandler INSTANCE = new ServerHandler();
	}

	private ServerHandler() {
	}

	@Override protected void channelRead0(ChannelHandlerContext ctx, GeneratedMessageV3 msg) throws Exception {
		System.out.println("========channelRead0=======");
		Hello hello = (Hello)msg;
		System.out.println(String.format("get message from client: %s", hello.getHello()));
		Hello helloResponse = Hello.newBuilder()
			.setHello("hello client, i'm server.")
			.build();
		ctx.channel().writeAndFlush(helloResponse);
	}

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("=======exceptionCaught=========");
		cause.printStackTrace();
		ctx.channel().close();
	}
}
