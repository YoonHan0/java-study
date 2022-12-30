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

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;

	public ChatServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// 1. Remote Host Information
		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remotePort = inetRemoteSocketAddress.getPort();
		log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

		try {
			// 2. 스트림 얻기
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 3. 요청 처리
			while (true) {
				String data = br.readLine();	// Client에서 Write한 data Read
				if (data == null) {
					log("closed by client");	// [EchoServer#21] closed by client
					break;
				}
				// 4. 프로토콜 분석
//				log("received:" + data);
//				pw.println(data);
				// ============================
				String[] tokens = data.split(":");
				this.nickname = tokens[0]; String message = tokens[1];
				userTalk(nickname, message);
				
				pw.println(message);
				
				
			}
		} catch (SocketException ex) {
			System.out.println("[server] suddenly closed by client");
		} catch (IOException ex) {
			log("error:" + ex);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

//	private void doJoin( String nickName, Writer writer ) {
//	   this.nickname = nickName;
//		   /* writer pool에  저장 */
//	}
	
	private void log(String message) {
		System.out.println("[EchoServer#" + getId() + "] " + message);
	}
	private void userTalk(String nickname, String message) {
		System.out.println(nickname + ":" + message);
	}
}
