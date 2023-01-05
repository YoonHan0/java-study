package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	List<String> users;
	List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters, List<String> users) {
		this.socket = socket;
		this.users = users;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		// 1. Remote Host Information
		InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		// System.out.println(inetRemoteSocketAddress);

		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remotePort = inetRemoteSocketAddress.getPort();
		log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

		try {
			// 2. 스트림 얻기
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 3. 요청 처리
			while (true) {
				String data = br.readLine(); // Client에서 Write한 data Read
				if (data == null) {
					log("클라이언트로 부터 연결 끊김"); // [EchoServer#21] closed by client
					doQuit(pw);
					break;
				}

				String[] tokens = data.split(":");

				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				}
				else if ("message".equals(tokens[0])) {

					if (">".equals(tokens[1])) { // 귓속말이면
						// System.out.println("입력받은 메시지 : " + tokens[2]); // 확인용 println()
						String[] nickname_message = tokens[2].split("-");

						doCheckUsers(nickname_message[0], nickname_message[1]);
					} else { // 귓속말이 아니면!
						doMessage(tokens[1]);
					}
				}
				else if ("quit".equals(tokens[0])) {
					doQuit(pw);
					pw.println("");
					break;
				} else {
					ChatServer.log("에러:알수 없는 요청(" + tokens[0] + ")");
				}
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
		System.out.println("서버 꺼짐");
	}
	/* 귓속말 */
	private void doCheckUsers(String nickname, String message) {
		PrintWriter printWriter = null;
		synchronized (listWriters) {
			synchronized (users) {
				for (int i = 0; i < users.size(); i++) {
					String name = users.get(i);
					
					if (name.equals(nickname)) {						
						printWriter = (PrintWriter) listWriters.get(i);
						printWriter.println(this.nickname + "님이 보낸 귓속말: " + message);	// 귓속말을 보낸 사용자 + 메시지
						printWriter.flush();
						return;
					}
				}
				System.out.println("존재하지 않는 사용자입니다!");		// 서버에 출력됨 -> 수정 필요할듯
			}
		}
		
	}
	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;

		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);

		/* writer pool에  저장 */
		addWriter(writer);

		// ack
		((PrintWriter) writer).println( "join:ok" );
	}

	private void doMessage(String message) {
		// nickname, message
		String data = this.nickname + ":" + message;
		broadcast(data);
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
			users.add(nickname);
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);
		log(data);
	}

	private void removeWriter(Writer writer) {
		listWriters.remove(writer);
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				// printWriter.flush();
			}
		}
	}

	private void log(String message) {
		System.out.println("[EchoServer#" + getId() + "] " + message);
	}
}
