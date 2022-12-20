package chapter03;

public class GoddsApp {

	public static void main(String[] args) {
		Goods camera = new Goods();
		camera.setName("nikon"); // 이름, setter를 이용해서 private 필드에 접근하여 값 할당
		camera.setPrice(400000); // 가격
		camera.setCountStock(30); // 재고량
		camera.setCountSold(50); //판매량
		
		camera.printInfo();
	}

}
