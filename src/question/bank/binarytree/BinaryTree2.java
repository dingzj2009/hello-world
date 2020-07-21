package question.bank.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 不同的二叉搜索树 II
 * 难度：中等
 */
public class BinaryTree2 {
	public static void main(String[] args){
		System.out.println("-----测试-----");
		Solution solution = new Solution();
		System.out.println("输入:" + 3);
		System.out.println("输出:");
		printTree(solution.generateTrees(3));
		System.out.println("------------------");
	}
	
	private static void printTree(List<TreeNode> treeList){
		if(treeList == null){
			return;
		}
		System.out.println("[");
		for(TreeNode treeNode : treeList){
			System.out.print(getTreeFromTopBottom(treeNode));
			System.out.println(",");
		}
		System.out.println("]");
	}
	
    public static List<String> getTreeFromTopBottom(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        while (queue.size() != 0) {
            TreeNode temp = queue.poll();
            list.add(temp.val + "");
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return list;
    }
	
}

/**
 * 递归
 */
class Solution {
	public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }
}

class TreeNode {
	 int val;
	 TreeNode left;
	 TreeNode right;
	 TreeNode() {}
	 TreeNode(int val) { this.val = val; }
	 TreeNode(int val, TreeNode left, TreeNode right) {
	 	this.val = val;
	 	this.left = left;
	 	this.right = right;
	 }
}
