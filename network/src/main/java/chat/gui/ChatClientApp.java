package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import chat.ChatClientThread;
import chat.ChatServer;

public class ChatClientApp {

	private static final String SERVER_IP = "127.0.0.1";
	
	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();

			if (!name.isEmpty()) { // 이전 코드 name.isEmpty == false
				break;
			}
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}

		scanner.close();

		// 1. create socket
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		
		try {
			socket = new Socket();
			// 2. connect to server
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			// 3. get iostream
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 4. join protocol 진행
			pw.println( "join:" + name );
			
			new ChatWindow(name, pw, br).show();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 		

	}
	public static void getData(String message, PrintWriter pw) {
		pw.println("message" + ":" + message);		
	}
}
