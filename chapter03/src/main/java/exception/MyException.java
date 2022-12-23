package exception;

public class MyException extends Exception {
	private static final long serialVersionUID = 1L;
	// 일반적으로 예외 클래스는 2개의 생성자를 만든다
	public MyException() {
		super("MyException Occurs");
	}
	
	public MyException(String message) {
		super(message);
	}
}
