package prob01;

public class MemberTest {

	public static void main(String[] args) {
		Member member = new Member();
		
		member.setName("윤한영");
		System.out.println(member.getName());
		
		member.setId("ID짱짱");
		System.out.println(member.getId());
		
		member.setPoint(20000);
		System.out.println(member.getPoint());
	}
}
