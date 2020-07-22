package question.bank.palindrome;

/**
 * 最长回文子串
 * 难度：中等
 */
public class LongestPalindrome {
	public static void main(String[] args){
		System.out.println("-----动态规划测试-----");
		String str = "babad";
		System.out.println("输入:" + str);
		System.out.println("输出:" + longestPalindrome_1(str));
		str = "abcd";
		System.out.println("输入:" + str);
		System.out.println("输出:" + longestPalindrome_1(str));
		System.out.println("------------------");
		System.out.println("-----中心扩展算法测试-----");
		str = "wabcabas";
		System.out.println("输入:" + str);
		System.out.println("输出:" + longestPalindrome_2(str));
		str = "abcd";
		System.out.println("输入:" + str);
		System.out.println("输出:" + longestPalindrome_2(str));
		System.out.println("------------------");
	}
	
	/**
	 * 动态规划
	 * 时间复杂度：O(n^2)，其中 n 是字符串的长度。动态规划的状态总数为 O(n^2)，对于每个状态，我们需要转移的时间为 O(1)。
	 * 空间复杂度：O(n^2)，即存储动态规划状态需要的空间。
	 * @param str
	 * @return
	 */
    public static String longestPalindrome_1(String str) {
        if(str == null || str.equals("")){
        	return "";
        }
        String palindrome = "";
        int size = str.length();
        boolean[][] dp = new boolean[size][size];
        // Arrays.fill(dp, false);
        for(int l=0; l < size; l++){
        	for(int i=0; i+l < size; i++){
        		int j = i + l;
        		if(l == 0){
        			dp[i][j] = true;
        		} else if (l == 1){
        			dp[i][j] = str.charAt(i) == str.charAt(j);
        		} else {
        			dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i+1][j-1];
        		}
        		if(dp[i][j] && l + 1 > palindrome.length()){
        			palindrome = str.substring(i, j+1);
        		}
        	}
        }
        return palindrome;
    }
	
	/**
	 * 中心扩展算法
	 * 时间复杂度：O(n^2)，其中 n 是字符串的长度。长度为 1 和 2 的回文中心分别有 n 和 n-1 个，每个回文中心最多会向外扩展 O(n) 次。
	 * 空间复杂度：O(1)。
	 * @param str
	 * @return
	 */
    public static String longestPalindrome_2(String str) {
    	if(str == null || str.equals("")){
        	return "";
        }
    	int start = 0, end = 0;
    	int size = str.length();
    	for(int i=0; i<size; i++){
    		int len1 = expandAroundCenter(str, i, i);
    		int len2 = expandAroundCenter(str, i, i+1);
    		int len = Math.max(len1, len2);
    		if(len > end - start){
    			start = i - (len-1)/2;
    			end = i + len/2;
    		}
    	}
    	return str.substring(start, end+1);
    }
    
    private static int expandAroundCenter(String str, int left, int right){
    	int L = left, R = right;
    	while(L >= 0 && R < str.length() && str.charAt(L) == str.charAt(R)){
    		L--;
    		R++;
    	}
    	return R - L - 1;
    }
	
}
