package org.example.tcp;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import org.example.model.RequestData;
import org.example.model.ResponseData;

/**
 * @author yuhangbin
 * @date 2022/7/26
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

	@Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
		RequestData data = new RequestData();
		data.setIntValue(9999);
		data.setStringValue("client");
		ChannelFuture channelFuture = ctx.writeAndFlush(data);
	}

	@Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println((ResponseData)msg);
		ctx.close();
	}
}
