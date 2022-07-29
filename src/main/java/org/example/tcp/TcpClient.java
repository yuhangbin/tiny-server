package org.example.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.model.RequestDataEncoder;
import org.example.model.ResponseDataDecoder;

/**
 * @author yuhangbin
 * @date 2022/7/24
 **/
public class TcpClient {

	public static void main(String[] args) throws Exception{
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new RequestDataEncoder(), new ResponseDataDecoder(), new ClientHandler());
				}
			});
			ChannelFuture f = b.connect(TcpServer.host, TcpServer.port).sync();

			f.channel().closeFuture().sync();
		}finally {
			workerGroup.shutdownGracefully();
		}
	}
}
