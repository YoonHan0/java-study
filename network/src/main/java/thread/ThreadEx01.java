package thread;

public class ThreadEx01 {

	public static void main(String[] args) {
		
//		for(int i = 0; i < 10; i++) {
//			System.out.print(i + " ");
//		}
		new DigitThread().start();		// Thread 객체 생성 + 실행(run)
		
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.print(c);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}

}
