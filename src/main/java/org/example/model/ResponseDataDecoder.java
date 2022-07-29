package org.example.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author yuhangbin
 * @date 2022/7/27
 **/
public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

	@Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ResponseData responseData = new ResponseData();
		responseData.setIntValue(in.readInt());
		out.add(responseData);
	}
}
