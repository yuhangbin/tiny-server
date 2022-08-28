package org.example.tcp.client.handler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protobuf.Hello;

/**
 * @author yuhangbin
 * @date 2022/8/28
 **/
public class ClientHandler extends SimpleChannelInboundHandler<GeneratedMessageV3> {

	public static class ClientHandlerHolder {
		public static final ClientHandler INSTANCE = new ClientHandler();
	}

	private ClientHandler(){

	}


	@Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("========channelRegistered=====");
		System.out.println(ctx.channel().remoteAddress().toString());
		Hello hello = Hello.newBuilder()
			.setHello("hello server, i'm client.")
			.build();
		ctx.channel().writeAndFlush(hello);
		System.out.println("write finish");
	}

	@Override protected void channelRead0(ChannelHandlerContext ctx, GeneratedMessageV3 msg) throws Exception {
		System.out.println("=========channelRead0========");
		Hello helloResponse = (Hello)msg;
		System.out.println(String.format("Get response : %s", helloResponse.getHello()));
	}

	@Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("=======exceptionCaught=========");
		cause.printStackTrace();
		ctx.channel().close();
	}
}
