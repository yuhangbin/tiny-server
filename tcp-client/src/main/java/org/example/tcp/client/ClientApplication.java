package org.example.tcp.client;

import org.example.tcp.client.client.ProtobufClient;
import org.example.tcp.client.client.TcpClient;

/**
 * @author yuhangbin
 * @date 2022/8/28
 **/
public class ClientApplication {

	public static void main(String[] args) {
		TcpClient tcpClient = new ProtobufClient();
		tcpClient.start();
	}
}
