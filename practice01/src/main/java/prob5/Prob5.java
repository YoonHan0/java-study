package prob5;

public class Prob5 {

	public static void main(String[] args) {
		int count = 1;
		String result = "";
		System.out.println(count + " " + result);
		while(count < 100) {	//100보다 크면 멈춤
			String strNumber = Integer.toString(count);
			for(int i = 0; i < strNumber.length(); i++) {
				char str = strNumber.charAt(i);
				if(str == '3' || str == '6' || str == '9') {
					result += "짝";
				}
			}
			if(result != "") {
				System.out.println(count + " " + result);
			}
			result = "";
			++count;
		}
	}
}
