package mypackage;

public class Goods2 {
	public String name;		// 모든 접근 허융(접근 제한 없음)
	protected int price;	// 같은 패키지 + 자식 클래스에서 접근이 가능*
	int countStock;			// 같은 패키지(디폴트 값)
	private int countSold;	// 클래스 내부에서만 접근이 가능
	
	public void m() {
		// private는 클래스 내부에서만 접근 가능
		countSold = 50;
	}
}
