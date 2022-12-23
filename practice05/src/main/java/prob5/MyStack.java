package prob5;

public class MyStack {
	private String[] arr;
	int index = -1;
	
	public MyStack(int size) {
		arr = new String[size];	
	}
	
	public void push(String value) {
		index++;
		arr[index] = value;
	}
	
	public String pop() {
		if(index < 0) throw new MyStackException();
		String popData = arr[index];
		index--;
		return popData;
	}
	
	public boolean isEmpty() {
		if(index < 0) return true;
		else return false;
	}
	
	
}