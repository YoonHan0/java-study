package prob5;

public class MyStack03<T> {	
	private T[] arr;	// 제네릭 타입 결정
	int index;
	
	@SuppressWarnings("unchecked")	//Warning 안 뜨게 하기 
	public MyStack03(int size) {
		index = -1;
		arr = (T[])new Object[size];	
	}
	
	public void push(T value) {
		index++;
		arr[index] = value;
	}
	
	public T pop() {
		if(index < 0) throw new MyStackException();
		T popData = arr[index];
		index--;
		return popData;
	}
	
	public boolean isEmpty() {
		return index < 0;	// 해당 식이 참이면 true / 거짓이면 false를 리턴
	}
	
	
}