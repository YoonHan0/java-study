package collection;

import java.util.HashMap;
import java.util.Map;
// 이터레이터가 없어서 순회가 안됨
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<>();

		m.put("one", 1);	// auto boxing이 일어남 (1에서)
		m.put("two", 2);
		m.put("three", 3);
		
		int i = m.get("one");	// auto unboxing
		int j = m.get(new String("one"));	// 값으로 찾아서 get을 하므로 가능함
		System.out.println(i + ":" + j);
		
		m.put("three", 33333);
		System.out.println(m.get("three"));
		
		// 순회
		Set<String> s = m.keySet(); // Set으로 값을 받아서 순회를 할 수 있다!
		for(String k : s) {
			System.out.println(m.get(k));
		}
	}

}
