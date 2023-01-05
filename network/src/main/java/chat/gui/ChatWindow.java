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
		textArea = new TextArea(20, 28);
		
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
		frame.addWindowListener(new WindowAdapter() {	// 창이 닫혔을 때
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
		pw.println("quit");
		
		// exit java(Application)
		System.exit(0);		// 프로그램이 끝날 때 '0'을 리턴해줘야함 -> return null
	}
	
	private void sendMessage(String name, PrintWriter pw) {
		String message = textField.getText();
		// System.out.println("메시지 보내는 프로토클 구현!! : " + message);	// TextField에 적힌 
		
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
					String data = bufferedReader.readLine();	// 서버에서 넘겨주는 값을 받음
					
					if (data == null) {		// 서버 강제 종료 시에
						updateTextArea("서버가 종료되어 자동으로 창이 닫힙니다"); 
						
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
					else if (data.equals("")) {		// quit
						updateTextArea("서버가 종료되어 5초 후 자동으로 창이 닫힙니다");
						
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
					else if(data.equals("join:ok")) {
						welcomeMessage();
					}
					else {
						updateTextArea(data);	// Client 본인에게 출력
					}
					/* 추가 */
					
				}
			} catch (IOException e) {
				ChatClient.log("Error" + e);
			} finally {
				System.exit(0);		// 종료되면서 부모도 끝낼 수 있
			}
		}
		private void welcomeMessage() {
			updateTextArea("입장하였습니다. 즐거운 시간되세요!!");
			updateTextArea("퇴장하는 명령어 : quit, 귓속말 양식은 \">:사용자이름-메시지\" 입니다!");
		}
	}
}
