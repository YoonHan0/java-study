package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import chat.ChatClient;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	
	private String name;
	private PrintWriter pw;
	private BufferedReader br;

	public ChatWindow(String name, PrintWriter pw, BufferedReader br) {	// 위젯
		this.name = name;
		this.pw = pw;
		this.br =br;
		
		frame = new Frame(this.name);	// 넘겨받은 name 사용
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.BLACK);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(name, pw);
			}
		});
		
		// 화살표 함수를 지원하는게 아니라 알아서 컴파일 해주는거
//		buttonSend.addActionListener((e) -> {	
//			
//		});
		
		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {	// Source/Override Implement Method...에서 설정
				char KeyCode = e.getKeyChar();
				if(KeyCode == KeyEvent.VK_ENTER) {	// Press Enter
					sendMessage(name, pw);
				}
			}
			
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
			
		});
		frame.setVisible(true);
		frame.pack();	//packing까지 하면 Window가 보임
		
		// IOStream 받아오기
		// ChatClientThread 생성하고 실행
		new ChatClientThread(br).start();
	}
	private void finish() {
		// quit protocol 작업
		
		
		// exit java(Application)
		System.exit(0);		// 프로그램이 끝날 때 '0'을 리턴해줘야함
	}
	
	private void sendMessage(String name, PrintWriter pw) {
		String message = textField.getText();
		System.out.println("메시지 보내는 프로토클 구현!! : " + message);	// TextField에 적힌 
		
		textField.setText("");	// 초기화
		textField.requestFocus();
		
		ChatClientApp.getData(message, pw);
		
		// ChatClientThread에서 서버로 부터 받음 메시지가 있다라고 치고
		// updateTextArea(name + ":" + message);
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private class ChatClientThread extends Thread {		// inner class
		// private Socket socket;
		private BufferedReader bufferedReader;

		public ChatClientThread(BufferedReader bufferedReader) {
			// this.socket = socket;
			this.bufferedReader = bufferedReader;
		}
		@Override
		public void run() {
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
					updateTextArea(data);	// Client 본인에게 출력
				}
			} catch (IOException e) {
				ChatClient.log("Error" + e);
			} finally {
				System.exit(0);		// 종료되면서 부모도 끝낼 수 있
			}
			// updateTextArea("안녕");
		}
	}

	
}
