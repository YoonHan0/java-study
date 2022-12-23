package prob06;

public abstract class Arith {
	private int a;	//아래의 get함수들을 지우고 private -> protected로 수정해서 자식 클래스에서 부모 필드변수를 직접 사용할 수도 있음!
	private int b;
	public void setValue(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int getAValue() {
		return a;
	}
	public int getBValue() {
		return b;
	}
	
	public abstract int calculate();
}
