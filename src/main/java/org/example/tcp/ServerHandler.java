package org.example.tcp;

import io.netty.channel.*;
import org.example.model.RequestData;
import org.example.model.ResponseData;

/**
 * @author yuhangbin
 * @date 2022/7/26
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		RequestData requestData = (RequestData)msg;
		ResponseData responseData = new ResponseData();
		responseData.setIntValue(requestData.getIntValue() * 2);
		ChannelFuture future = ctx.writeAndFlush(responseData);
		future.addListener(ChannelFutureListener.CLOSE);
		System.out.println(requestData);
	}
}
