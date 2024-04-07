package org.example.tcp.client.client;

/**
 * @author yuhangbin
 * @date 2022/8/28
 **/
public interface TcpClient {

	void start();

	boolean send(Object msg);

	void stop();
}
