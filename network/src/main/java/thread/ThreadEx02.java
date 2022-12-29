package thread;

/* 멀티스레드에서 Thread가 하나라도 남아있으면 프로그램은 종료되지 않는다 */
public class ThreadEx02 {

	public static void main(String[] args) {
		Thread thread01 = new DigitThread();	// 스레드 객체 생성
		Thread thread02 = new AlphableThread();	// 스레드 객체 생성
		Thread thread03 = new Thread(new UpperCaseAlphabetRunnablelmpl());
		
		thread01.start();
		thread02.start();
		thread03.start();

	}

}
