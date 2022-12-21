package chapter03;

public class GoddsApp {

	public static void main(String[] args) {
		Goods camera = new Goods();
		camera.setName("nikon"); // 이름, setter를 이용해서 private 필드에 접근하여 값 할당
		camera.setPrice(400000); // 가격
		camera.setCountStock(30); // 재고량
		camera.setCountSold(50); //판매량
		
		camera.printInfo();

		//		정보은닉(데이터보호)
		camera.setPrice(-1);
		
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		System.out.println(Goods.countOfGoods);
		
		camera.setPrice(4000000);
		System.out.println(camera.calcDiscountPrice(0.5f));
	}

}
