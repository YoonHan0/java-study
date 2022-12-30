package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import chat.ChatServer;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	public static void main(String[] args) {
		Socket socket = null;

		Scanner scanner = null;

		try {
			socket = new Socket();

			scanner = new Scanner(System.in);
			System.out.print("닉네임 >> ");
			String nickname = scanner.nextLine();
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			welcomeMessage();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

//			String data = br.readLine();	// message만 넘겨받음
//			if(data != null) {
//				String[] tokens = data.split(":");
//				nickname = tokens[0]; String message = tokens[1];
//				System.out.println(nickname + ":" + message);
//			}
			
			while (true) {

				System.out.print("> ");
				String line = scanner.nextLine();
				if ("exit".equals(line)) {
					break;
				}
				String nameLine = nickname + ":" + line;
				pw.println(nameLine);

				String data = br.readLine(); // message만 넘겨받음
				if (data == null) {
					log("closed by server");
					break;
				}
				String[] tokens = data.split(":");
				nickname = tokens[0];
				String message = tokens[1];
				System.out.println(nickname + ":" + message); ////////////// 그만 밀어어어어어어어어 //////////////
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
				if (scanner != null) {
					scanner.close();
				}
			} catch (IOException e) {
				log("error: " + e);
			}
		}
	}

	private static void log(String message) {
		System.out.println("[EchoClient] " + message);
	}
	private static void welcomeMessage() {
		System.out.println("입장하였습니. 즐거운 시간되세요!!");
	}

	
}
