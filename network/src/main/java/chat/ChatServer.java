package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chat.ChatServerThread;

public class ChatServer {
	public static final int PORT = 8000;
	private static List<Writer> arrayWriter = new ArrayList<>();
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 8000), 10);
			log("starts...[port:" + PORT + "]");

			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, arrayWriter).start();
			}
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void log(String message) {
		// System.out.println(Thread.currentThread()); // Thread[#1,main,5,main]
		// System.out.println(Thread.currentThread().getName()); //main

		System.out.println("[EchoServer#" + Thread.currentThread().getId() + "] " + message);
	}

}
