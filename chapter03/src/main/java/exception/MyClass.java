package exception;

import java.io.IOException;

public class MyClass {

	public void danger() throws IOException, MyException {
		System.out.println("Some Code1...");
		System.out.println("Some Code2...");
		if(3 - 3 == 0) {
			throw new MyException();
		}
		if(5 - 5 == 0) {
			throw new IOException();
		}
		System.out.println("Some Code3...");
		System.out.println("Some Code4...");
		
		
	}
}
