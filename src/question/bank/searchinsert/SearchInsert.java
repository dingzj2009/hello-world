package question.bank.searchinsert;

/**
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 难度：简单
 */
public class SearchInsert {
	public static void main(String[] args){
		System.out.println("-----二分查找法测试-----");
		int[] nums = new int[]{1,3,5,6};
		int target = 5;
		System.out.println("输入target:" + target + "; 输出:" + searchInsert(nums, target));
		target = 2;
		System.out.println("输入target:" + target + "; 输出:" + searchInsert(nums, target));
		target = 7;
		System.out.println("输入target:" + target + "; 输出:" + searchInsert(nums, target));
		target = 0;
		System.out.println("输入target:" + target + "; 输出:" + searchInsert(nums, target));
		System.out.println("------------------");
	}
	
	public static int searchInsert(int[] nums, int target) {
		int n = nums.length;
		int left = 0;
		int right = n - 1;
		int pos = n; //返回插入位置
		while(left <= right){
			int mid = ((right - left) >> 1) + left; // 或者 (right + left) / 2;
			if(target <= nums[mid]){
				pos = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return pos;
	}
	
}

