package collection;

import java.util.Enumeration;
import java.util.Vector;

/*collection 방식이 아닌 예전 방식을 사용해서 구현*/
public class VectorTest01 {

	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		
		
		v.addElement("둘리..?");
		v.addElement("마이콜 쒯");
		v.addElement("도우너");
		
		// 순회1
		for(int i = 0; i < v.size(); i++) {
			String s = v.elementAt(i);
			System.out.println(s);
		}
		
		// 삭제
		v.removeElementAt(2);
		
		System.out.print("\n");
		
		// 순회2
		Enumeration<String> e = v.elements();
		while(e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
		}
	}

}
