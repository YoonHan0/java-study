package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

				/*Collection FrameWork 사용*/
public class ArrayListTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();	// 요기 이름만 바꿔줌
		
		
		list.add("둘리..?");
		list.add("마이콜 쒯");
		list.add("도우너");
		
		// 순회1
		for(int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			System.out.println(s);
		}
		
		// 삭제
		list.remove(2);
		
		System.out.print("\n");
		
		// 순회2
		Iterator<String> it = list.iterator();
		while((it.hasNext())) {
			String s = it.next();
			System.out.println(s);
		}
		
		System.out.print("\n");
		
		// 순회3
		for(String s : list) {
			System.out.println(s);
		}
	}

}
