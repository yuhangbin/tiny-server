package org.example.tcp.server.handler;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.protobuf.Hello;

/**
 * @author yuhangbin
 * @date 2022/8/27
 **/
public class ServerHandler extends SimpleChannelInboundHandler<GeneratedMessageV3> {

	public static class ServerHandlerHolder{
		public static final ServerHandler INSTANCE = new ServerHandler();
	}

	private ServerHandler() {
	}

	@Override protected void channelRead0(ChannelHandlerContext ctx, GeneratedMessageV3 msg) throws Exception {

	}
}
