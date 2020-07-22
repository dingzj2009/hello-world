package question.bank.array;

/**
 * 旋转数组的最小数字
 * 难度：简单
 */
public class MinArrayNum {
	public static void main(String[] args){
		System.out.println("-----测试-----");
		int[] numbers = new int[]{3,4,5,1,2};
		System.out.println("输入:" + getStr(numbers));
		System.out.println("输出:" + minArray(numbers));
		System.out.println("------------------");
	}
	
	private static String getStr(int[] numbers){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int num : numbers){
			sb.append(num).append(",");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static int minArray(int[] numbers) {
		int low = 0;
		int high = numbers.length - 1;
		while(low < high){
			int mid = low + (high - low) / 2;
			if(numbers[mid] > numbers[high]){
				low = mid + 1;
			} else if(numbers[mid] < numbers[high]){
				high = mid;
			} else {
				high = high - 1;
			}
		}
		return numbers[low];
	}
	
}
