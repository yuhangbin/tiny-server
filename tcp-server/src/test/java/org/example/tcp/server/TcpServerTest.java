package org.example.tcp.server;

import org.example.tcp.server.server.ProtobufServer;
import org.example.tcp.server.server.TcpServer;
import org.junit.jupiter.api.Test;

/**
 * @author yuhangbin
 * @date 2022/8/27
 **/
public class TcpServerTest {

	public static void main(String[] args) throws Exception{
		try {
			System.out.println("hello");
			Thread.sleep(10000);
		}finally {
			System.out.println("finally");
		}
	}
}
