package collection;

import java.util.LinkedList;
import java.util.Queue;

// Queue는 클래스가 아닌 인터페이스이다?!
// Spring은 웹프로그래밍을 위해서 만들어진게 아니라 이외에도 인프라 구성 등등등 많음

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> q = new LinkedList<>();
		
		q.offer("마이콜");	// 값을 넣음
		q.offer("둘리");
		q.offer("도우너");
		
		while(!q.isEmpty()) {
			String s = q.poll();
			System.out.println(s);
		}
		
		q.offer("마이콜");
		q.offer("둘리");
		q.offer("도우너");
		
		System.out.println(q.poll());
		System.out.println(q.peek());
		System.out.println(q.poll());
		System.out.println(q.poll());
		
		System.out.println(q.poll());	// 안에 빈 상태로 poll()
		// 비어 있는 경우 null 반환
	}

}
