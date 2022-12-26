package prob01;

public class Printer {

	// 오버로드
//	public void println(int data) {
//		System.out.println(data);
//	}
//	public void println(boolean data) {
//		System.out.println(data);
//	}
//	public void println(double data) {
//		System.out.println(data);
//	}
//	public void println(String data) {
//		System.out.println(data);
//	}
	
	public <T> void println(T t) {
		System.out.println(t);
	}
	/*가변인자*/
	public int sum(Integer... nums) {
		int s = 0;
		for(Integer n:nums) {
			s += n;
		}
		
		return s;
	}
	/*재네릭타입 + 가변인자*/
	public <T> void println(T... ts) {
		for(T t:ts) {
			System.out.println(t);
		}
	}
}
