package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Scanner;

import chat.ChatServer;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	public static void main(String[] args) {
		
		Socket socket = null;
		Scanner scanner = null;

		try {
			// 1. 키보드 연결
			// 2. 소켓 생성
			socket = new Socket();
			// 3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			
			// 4. reader / writer 생
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 5. join 프로토콜 생성
			scanner = new Scanner(System.in);
			System.out.print("닉네임 >> ");
			String nickname = scanner.nextLine();
			pw.println( "join:" + nickname );

			new ChatClientThread(socket, br).start();
						
			while(true) {
				System.out.print( ">>" );
				String input = scanner.nextLine();
				
				if( "quit".equals( input )) { // || input == null
					// 8. quit 프로토콜 처리
					pw.println("quit");
					log("closed by server");
					break;
				}
				else {
					// 9. 메시지 처리
					pw.println("message:" + input);
					pw.flush();
				}
			}			

		} catch (SocketException ex) {
			log("suddenly closed by client : " + ex);
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				log("error: " + e);
			}
		}
	}

	public static void log(String message) {
		System.out.println("[EchoClient] " + message);
	}
}
