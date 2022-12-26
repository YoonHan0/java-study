package prob5;

public class MyStack {
	private Object[] arr;
	int index;
	
	public MyStack(int size) {
		index = -1;
		arr = new String[size];	
	}
	
	public void push(Object value) {
		index++;
		arr[index] = value;
	}
	
	public Object pop() {
		if(index < 0) throw new MyStackException();
		Object popData = arr[index];
		index--;
		return popData;
	}
	
	public boolean isEmpty() {
		return index < 0;	// 해당 식이 참이면 true / 거짓이면 false를 리턴
	}
	
	
}