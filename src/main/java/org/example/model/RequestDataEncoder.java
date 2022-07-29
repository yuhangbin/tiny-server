package org.example.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author yuhangbin
 * @date 2022/7/26
 **/
public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

	private final Charset charset = StandardCharsets.UTF_8;

	@Override protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
		out.writeInt(msg.getIntValue());
		out.writeInt(msg.getStringValue().length());
		out.writeCharSequence(msg.getStringValue(), charset);
	}
}
