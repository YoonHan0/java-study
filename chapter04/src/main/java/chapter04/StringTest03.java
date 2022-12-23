package chapter04;

/* StringBuffer */ /* String Method들.... */
public class StringTest03 {

	public static void main(String[] args) {
		// String s1 = "Hello " + "World " + "Java " + "17";
		String s1 = new StringBuffer("Hello ")
			.append("World ")
			.append("Java ")
			.append(17)
			.toString();
		
		/* 이런 식으로 for문 안에 StringBuffer 사용해서 CPU 사용량을 늘리지마셈 */
		String s2 = "";
		for(int i = 0; i < 1000000; i++) {
			s2 = new StringBuffer(s2).append(i).toString();
		}
		/* 이렇게 사용 */
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < 10; i++) {
			sb.append(i);
		}
		String s3 = sb.toString();
		
		/* String Method들.... */
		String s4 = "ABCDEFGHI";
		
		System.out.println(s4.length());
		System.out.println(s4.charAt(2));
		System.out.println(s4.indexOf("ABC"));
		System.out.println(s4.indexOf("ABC", 7));
		System.out.println(s4.substring(3));
		System.out.println(s4.substring(3, 5));
		
		String s5 = "   ab   cd   ";
		String s6 = "윤한영,2022-12-23,금요일";
		
		String s7 = s5.concat(s6);
		System.out.println(s7);
		
		System.out.println("---" + s5.trim() + "---");
		System.out.println("---" + s5.replaceAll(" ", "") + "---");
	}

}
