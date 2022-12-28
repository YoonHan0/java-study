package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();

			// 2. binding (주소를 소켓에다가 붙히는 작업)
			// Socket에 InetSocketAddress( = IP Address + Port)를 바인딩한다.
			// IPAddress : 0.0.0.0 : 특정 호스트 IP에 바인딩하지 않는다.

			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000)); // InetAddress.getLocalHost().getHostAddress() =>
																		// 자기 컴퓨터의 IP 구하기

			// 3. accept
			Socket socket = serverSocket.accept(); // 여기서 프로그램 blocking

			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remoteHostPort = inetRemoteSocketAddress.getPort();
				// 3-1. telnet IP주소 Port번호 -> connect
				System.out.println("[server] connected by client [" + remoteHostAddress + " : " + remoteHostPort + "]");
				
				// 4. IO Stream 받아오기
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				
				
				while(true) {
					// 5. 데이터 읽기(클라이언트에서 먼저 작성함)
					byte[] buffer = new byte[256];
					
					int readByteCount = is.read(buffer); // blocking ?: 데이터를 입력하지 않으면 다음으로 넘어가지 않으니까
					if(readByteCount == -1) {
						// 서버 정상 종료(close() 호출)
						System.out.println("[sever] closed by client");
						break;
					}
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received: " + data);
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}
			} catch (IOException e) {
				System.out.println("[server] error" + e);
			} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			System.out.println("[server] error" + e);
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

}
