package question.bank.sqrtnumber;

/**
 * 难度：简单
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 */
public class SqrtNumber {
	public static void main(String[] args){
		System.out.println("-----二分查找法测试-----");
		System.out.println("根号 4 结果:" + mySqrt_1(4));
		System.out.println("根号 8 结果:" + mySqrt_1(8));
		System.out.println("根号 15 结果:" + mySqrt_1(15));
		System.out.println("------------------");
		System.out.println("-----牛顿迭代法测试-----");
		System.out.println("根号 4 结果:" + mySqrt_2(4));
		System.out.println("根号 8 结果:" + mySqrt_2(8));
		System.out.println("根号 15 结果:" + mySqrt_2(15));
		System.out.println("------------------");
	}
	
	/**
	 * 1:二分查找
	 * 由于 xx 平方根的整数部分 ans 是满足 k^2 <= x 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
	 * 二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，我们只需要比较中间元素 mid 的平方与 x 的大小关系，并通过比较的结果调整上下界的范围。
	 * 由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 ans 后，也就不需要再去尝试 ans+1 了。
	 * 
	 * 时间复杂度：O(log x)，即为二分查找需要的次数; 空间复杂度：O(1)。
	 * @param x
	 * @return
	 */
	public static  int mySqrt_1(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }

	/**
	 * 2:牛顿迭代
	 * 
	 * 时间复杂度：O(log x)，此方法是二次收敛的，相较于二分查找更快; 空间复杂度：O(1)。
	 * @param x
	 * @return
	 */
	public static int mySqrt_2(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }
}
