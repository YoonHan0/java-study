package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ChatClientThread extends Thread {

	private Socket socket;
	private BufferedReader bufferedReader;

	public ChatClientThread(Socket socket, BufferedReader bufferedReader) {
		this.socket = socket;
		this.bufferedReader = bufferedReader;
	}

	@Override
	public void run() {
		/* reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리) */
		try {
			while (true) {
				String data = bufferedReader.readLine();
				
				if (data == null) {
					ChatClient.log("서버 강제 종료!"); // 서버 강제 종료
					break;
				}
				else if (data.equals("")) {
					ChatClient.log("서버로 부터 연결 끊김"); // quit!
					break;
				}
				/* 추가 */
				System.out.println(data);	// Client 본인에게 출력
			}
		} catch (IOException e) {
			ChatClient.log("Error" + e);
		} finally {
			System.exit(0);		// 종료되면서 부모도 끝낼 수 있
		}

	}
}
