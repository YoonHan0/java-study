package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;
		
		System.out.println("Some Code1... / file open...!");
		
		try { 
			System.out.println("Some Code2...");
			System.out.println("Some Code3...");
			
			int result = (1 + 2 + 3) / b;
			
			System.out.println("Some Code4...");
			System.out.println("Some Code5...");
		} catch(ArithmeticException ex) {
			/* 예외 처리 */
			// 1. 로깅
			System.out.println("error: " + ex);
			
			// 2. 사과
			System.out.println("미안합니다...");
			
			// 3. 정상종료
			return;	// 종료
			// System.exit(0);	// exit(0)이면 정상 종료, 다른 것들은 에러를 출력함?
		} finally {
			System.out.println("자원 정리 예: file close...");
		}
		// 여기 부분(예외가 발생한 후)에는 굳이 코드를 넣지말자(한다면 return정도)
		// 예외를 catch 못할 경우(=> 어차피 잘못된 경우)에 원하는 값이 나오지 않거나 
		// 예외가 발생한다면 어차피 실행되지 않을 영역
		System.out.println("Some Code6...");
		System.out.println("Some Code7...");	
	}
}
