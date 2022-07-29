package org.example.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author yuhangbin
 * @date 2022/7/27
 **/
public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {

	@Override protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) throws Exception {
		out.writeInt(msg.getIntValue());
	}
}
