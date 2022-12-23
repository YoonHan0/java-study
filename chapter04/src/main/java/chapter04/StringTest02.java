package chapter04;

// 자바 기본 내장 객체
// toUpperCase(), concat()
public class StringTest02 {

	public static void main(String[] args) {
		// immutability (불변성)
		String s1 = "abc";
		String s2 = "def";
		String s3 = s2;
		
		s2 = s1.toUpperCase();
		String s4 = s2.concat("??");
		String s5 = "!".concat(s2).concat("@");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		
		System.out.println(equalsHello("Hello!"));
	}
	private static boolean equalsHello(String s) {
		return "Hello".equals(s);
	}
}
