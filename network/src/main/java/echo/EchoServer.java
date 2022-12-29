package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static final int PORT = 8000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {

			serverSocket = new ServerSocket();

			serverSocket.bind(new InetSocketAddress("0.0.0.0", 8000));
			log("starts... [port " + PORT + "]");
			Socket socket = serverSocket.accept();

			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			
			log("connected by client [" + remoteHostAddress + " : " + remoteHostPort + "]");

			try {
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // true : 버퍼가 다 채워지지 않아도 전송할 수 있게 하는 파라미터
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")); // 개행을 붙여서 연속되는 스트림의 경계로 표현된다.

				
				while (true) {
					String data = br.readLine();
					
					if(data == null) {
						log("closed by client");
						break;
					}
					log("received: " + data);
					pw.println(data);	
					//print(버퍼에 쌓기만하고 flush X) vs println
				}
			} catch (IOException e) {
				log("[server] error" + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			log("error" + e);
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
		System.out.println("[EchoServer] " + message);
	}

}
