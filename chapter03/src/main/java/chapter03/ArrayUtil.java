package chapter03;

public class ArrayUtil {
	public static double[] intToDouble(int[] is) {
		double[] result = new double[is.length];	// 넘어오는 배열의 크기만큼
		
		for(int i = 0; i < is.length; i++) {
			result[i] = is[i];
		}
		
		return result;
	}
	
	public static int[] doubleToInt(double[] is) {
		int[] result = new int[is.length];
		
		for(int i = 0; i < is.length; i++) {
			result[i] = (int)is[i];
		}
		return result;
	}
	
	public static int[] concat(int[] num1, int[] num2) {
		int[] result = new int[num1.length + num2.length];

//		for(int i = 0; i < result.length; i++) {
//			if(i < num1.length) {
//				result[i] = num1[i];
//			}
//			else {
//				result[i] = num2[i-num1.length];
//			}
//		}
		System.arraycopy(num1, 0, result, 0, num1.length);
		System.arraycopy(num2, 0, result, num1.length, num2.length);
		return result;
	}
}
