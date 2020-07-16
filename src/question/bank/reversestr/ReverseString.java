package question.bank.reversestr;

/**
 * 难度：简单
 * 反转字符串
 *
 */
public class ReverseString {
	public static void main(String[] args){
		System.out.println("-----递归法测试-----");
		char[] s = new char[]{'a','b','c','d'};
		System.out.print("原字符串:" + new String(s));
		reverseString_1(s);
		System.out.println("; 反转结果:" + new String(s));
		System.out.println("------------------");
		System.out.println("-----双指针法法测试-----");
		s = new char[]{'h','e','l','l','o'};
		System.out.print("原字符串:" + new String(s));
		reverseString_2(s);
		System.out.println("; 反转结果:" + new String(s));
		System.out.println("------------------");
	}

	/**
	 * 方法一：递归
	 * 思路：
	 * 我们实现递归函数 helper，它接受两个参数：left 左指针和 right 右指针。
	 * 如果 left>=right，不做任何操作。 否则交换 s[left] 和 s[right] 和调用 helper(left + 1, right - 1)。
	 * 首次调用函数我们传递首尾指针反转整个字符串 return helper(0, length(s) - 1)。
	 * 
	 * 时间复杂度：O(N)。执行了 N/2 次的交换。
	 * 空间复杂度：O(N)，递归过程中使用的堆栈空间。
	 * @param s
	 */
	public static void reverseString_1(char[] s) {
		helper(s, 0, s.length - 1);
	}
	
	public static void helper(char[] s, int left, int right) {
	    if (left >= right) return;
	    char tmp = s[left];
	    s[left++] = s[right];
	    s[right--] = tmp;
	    helper(s, left, right);
	}
	
	/**
	 * 方法二：双指针法
	 * 时间复杂度：O(N)。执行了 N/2 次的交换。
	 * 空间复杂度：O(1)，只使用了常数级空间。
	 * @param s
	 */
	public static void reverseString_2(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

}
