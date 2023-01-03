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
	List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
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
				String data = br.readLine();	// Client에서 Write한 data Read
				if (data == null) {
					log("클라이언트로 부터 연결 끊김");	// [EchoServer#21] closed by client
					doQuit(pw);
					break;
				}
				// 4. 프로토콜 분석
//				log("received:" + data);
//				pw.println(data);
				// ============================
				String[] tokens = data.split(":");
				if( "join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				}
				else if( "message".equals( tokens[0] ) ) {
					doMessage(tokens[1]);
				}
				else if( "quit".equals( tokens[0] ) ) {
					doQuit(pw);
					pw.println("");
					break;
				}
				else {
					ChatServer.log( "에러:알수 없는 요청(" + tokens[0] + ")" );
				}
				
				
//				this.nickname = tokens[0]; String message = tokens[1];
//				
//				userTalk(nickname, message);
//				
//				System.out.println(arrayWriter.size());
//				for(int i = 0; i < arrayWriter.size(); i++) {
//					PrintWriter printWriter = (PrintWriter) arrayWriter.get(i);
//					printWriter.println(data);
//				}
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

	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;
	
		String data = nickName + "님이 참여하였습니다.";
		broadcast( data );
		
		/* writer pool에  저장 */
		addWriter(writer);
		
		 // ack
		// ((PrintWriter) writer).println( "join:ok" );
		// writer.flush();
		
	}
	private void doMessage( String message ) {
		// nickname, message
		String data = this.nickname + ":" + message;
		broadcast(data);
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	private void doQuit( Writer writer ) {
		removeWriter( writer );
		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast( data );
		log(data);
	}
	private void removeWriter( Writer writer ) {
		listWriters.remove(writer);
	}


	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	private void log(String message) {
		System.out.println("[EchoServer#" + getId() + "] " + message);
	}

//	private void userTalk(String nickname, String message) {
//		System.out.println(nickname + ":" + message);
//	}
}
