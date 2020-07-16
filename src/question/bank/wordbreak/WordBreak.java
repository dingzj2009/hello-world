package question.bank.wordbreak;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 难度：中等
 * 动态规划解法
 */
public class WordBreak {
	
	public static void main(String[] args){
		System.out.println("-----动态规划1测试-----");
		String str = "leetcode";
		List<String> wordDict = Arrays.asList("leet", "code");
		System.out.println("str:" + str + ", wordDict:" + wordDict + ", result:" + wordBreak_1(str, wordDict));
		str = "applepenapple";
		wordDict = Arrays.asList("apple", "pen");
		System.out.println("str:" + str + ", wordDict:" + wordDict + ", result:" + wordBreak_1(str, wordDict));
		str = "catsandog";
		wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
		System.out.println("str:" + str + ", wordDict:" + wordDict + ", result:" + wordBreak_1(str, wordDict));
		System.out.println("------------------");
		System.out.println("-----动态规划2测试-----");
		str = "leetcode";
		wordDict = Arrays.asList("leet", "code");
		System.out.println("str:" + str + ", wordDict:" + wordDict + ", result:" + wordBreak_2(str, wordDict));
		str = "applepenapple";
		wordDict = Arrays.asList("apple", "pen");
		System.out.println("str:" + str + ", wordDict:" + wordDict + ", result:" + wordBreak_2(str, wordDict));
		str = "catsandog";
		wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
		System.out.println("str:" + str + ", wordDict:" + wordDict + ", result:" + wordBreak_2(str, wordDict));
		System.out.println("------------------");
	}
	
	/**
	 * 时间复杂度：O(n^2) ，其中 n 为字符串 s 的长度。我们一共有 O(n) 个状态需要计算，每次计算需要枚举 O(n)个分割点，
	 * 	        哈希表判断一个字符串是否出现在给定的字符串列表需要 O(1) 的时间，因此总时间复杂度为 O(n^2)。
	 * 空间复杂度：O(n) ，其中 n 为字符串 s 的长度。我们需要 O(n) 的空间存放 dp 值以及哈希表亦需要 O(n) 的空间复杂度，因此总空间复杂度为 O(n)。
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean wordBreak_1(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<String>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

	
	public static boolean wordBreak_2(String s, List<String> dict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < dict.size(); j++) {
                String str = dict.get(j);
                int length = str.length();
                if (length <= i && dp[i - length] && str.equals(s.substring(i - length, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

}

