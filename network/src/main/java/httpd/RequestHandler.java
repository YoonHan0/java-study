package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENT_ROOT = "./webapp";
	private Socket socket;
	
	public RequestHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort());
			
			String request = null;
			while(true) {
				String line = br.readLine();
				
				// 브라우저 연결을 끊으면
				if(line == null) {
					break;
				}
				
				// SimpleHttpServer는 요청의 헤더만 처리한다.
				if("".equals(line)) {
					break;
				}
				
				// 요청 헤더의 첫 번째 라인만 읽음
				if(request == null) {
					request = line;
					break;
				}
			}
			
			// 요청 처리
			consoleLog(request);
			// GET / HTTP/1.1
			// GET /assets/css/mysite.css HTTP/1.1
			// GET /assets/images/profile.jpg HTTP/1.1
			// GET /favicon.ico HTTP/1.1
			
			String[] tokens = request.split(" ");
			if("GET".equals(tokens[0])) {
				reponseStaticResource(outputStream, tokens[1], tokens[2]);
			} else {
				// methods: POST, PUT, DELETE, HEAD, CONNECT
				// SimpleHttpServer 에서는 무시(400 Bad Resquest)
				reponse400Error(outputStream, tokens[2]);
			}
			
			
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			// outputStream.write("HTTP/1.1 200 OK\r\n".getBytes("UTF-8"));
			// outputStream.write("Content-Type:text/html; charset=utf-8\r\n".getBytes("UTF-8"));
			// outputStream.write("\r\n".getBytes() );
			// outputStream.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes( "UTF-8" ) );
			
		} catch(Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try{
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
				
			} catch(IOException ex) {
				consoleLog( "error:" + ex );
			}
		}			
	}

	private void reponseStaticResource(
		OutputStream outputStream,
		String url,
		String protocol) throws IOException {
		
		// default(welcome) file set
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		if(!file.exists()) {
			reponse404Error(outputStream, protocol);
			return;
		}
		
		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		// 응답
		outputStream.write((protocol + " 200 OK\r\n").getBytes("UTF-8"));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes());
		outputStream.write(body);
	}

	private void reponse404Error(OutputStream outputStream, String protocol) {
		// HTTP/1.1 404 Not Found
		// Content-Type:.....
		// \r\n
		// .....
		String url = "/error/404.html";
		File file = new File(DOCUMENT_ROOT + url);
		
		byte[] body;
		try {
			body = Files.readAllBytes(file.toPath());
			String contentType = Files.probeContentType(file.toPath());
			
			/*
			 * contentType : text/html | file.toPath() : .\webapp\error\400.html |  protocol : HTTP/1.1
			 */
			
//			System.out.println("contentType : " + contentType + " || " + "file.toPath() : " + file.toPath());
//			System.out.println("protocol : " + protocol);
			
			outputStream.write((protocol + " 404 Not Found\r\n").getBytes("UTF-8"));
			outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
			outputStream.write("\r\n".getBytes());
			outputStream.write(body);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void reponse400Error(OutputStream outputStream, String protocol) {
		// HTTP/1.1 400 Bad Request
		// Content-Type:.....
		// \r\n
		// .....
		String url = "/error/400.html";
		File file = new File(DOCUMENT_ROOT + url);
		
		byte[] body;
		try {
			body = Files.readAllBytes(file.toPath());
			String contentType = Files.probeContentType(file.toPath());
			/*
			 * contentType : text/html | file.toPath() : .\webapp\error\400.html |  protocol : HTTP/1.1
			 */
//			System.out.println("contentType : " + contentType + " || " + "file.toPath() : " + file.toPath());
//			System.out.println("protocol : " + protocol);
			
			outputStream.write((protocol + " 400 Bad Request\r\n").getBytes("UTF-8"));
			outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
			outputStream.write("\r\n".getBytes());
			outputStream.write(body);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void consoleLog( String message ) {
		System.out.println( "[httpd#" + getId() + "] " + message );
	}
}