package prob5;

// 재네릭 사용
public class MainApp03 {

	public static void main(String[] args) {
		try {
			MyStack03<String> stack = new MyStack03<>(6); // 재네릭 파라미터를 사용하겠다
			stack.push("Hello");
			stack.push("World");
			stack.push("!!!");
			stack.push("1");
			stack.push(".");

			while (stack.isEmpty() == false) {
				String s = stack.pop();
				System.out.println( s );
			}

			System.out.println("======================================");

			stack = new MyStack03<>(3);
			stack.push("Hello");

			System.out.println(stack.pop());
			System.out.println(stack.pop());
			
		} catch ( MyStackException ex) {
			System.out.println( ex );
		}

	}

}
