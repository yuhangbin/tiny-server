package org.example.tcp.server;

import org.example.tcp.server.server.ProtobufServer;
import org.example.tcp.server.server.TcpServer;

/**
 * @author yuhangbin
 * @date 2022/8/27
 **/
public class ServerApplication {

	public static void main(String[] args) {
		TcpServer tcpServer = new ProtobufServer();
		try {
			tcpServer.start();
		}finally {
			tcpServer.shutdown();
		}
	}
}
