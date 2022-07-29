package org.example.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yuhangbin
 * @date 2022/7/27
 **/
public class RequestDataDecoder extends ReplayingDecoder<RequestData> {

	private final Charset charset = StandardCharsets.UTF_8;

	@Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		RequestData requestData = new RequestData();
		requestData.setIntValue(in.readInt());
		int strLen = in.readInt();
		requestData.setStringValue(
			in.readCharSequence(strLen, charset).toString()
		);
		out.add(requestData);
	}
}
