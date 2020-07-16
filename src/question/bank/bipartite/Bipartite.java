package question.bank.bipartite;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二分图
 * 难度：中等
 */
public class Bipartite {
	public static void main(String[] args){
		System.out.println("-----深度优先测试-----");
		int[][] graph = new int[][]{{1,3}, {0,2}, {1,3}, {0,2}};
		SolutionDfs dfs = new SolutionDfs();
		System.out.println("输入:" + graph + "; 输出:" + dfs.isBipartite(graph));
		graph = new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}};
		System.out.println("输入:" + graph + "; 输出:" + dfs.isBipartite(graph));
		System.out.println("------------------");
		System.out.println("-----广度优先测试-----");
		graph = new int[][]{{1,3}, {0,2}, {1,3}, {0,2}};
		SolutionBfs bfs = new SolutionBfs();
		System.out.println("输入:" + graph + "; 输出:" + bfs.isBipartite(graph));
		graph = new int[][]{{1,2,3}, {0,2}, {0,1,3}, {0,2}};
		System.out.println("输入:" + graph + "; 输出:" + bfs.isBipartite(graph));
		System.out.println("------------------");
	}
}

/**
 * 深度优先搜索
 * 时间复杂度：O(N+M)，其中 N 和 M 分别是无向图中的点数和边数。
 * 空间复杂度：O(N)，存储节点颜色的数组需要 O(N) 的空间，并且在深度优先搜索的过程中，栈的深度最大为 NN，需要 O(N) 的空间。
 */
class SolutionDfs {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    private boolean valid;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        valid = true;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n && valid; ++i) {
            if (color[i] == UNCOLORED) {
                dfs(i, RED, graph);
            }
        }
        return valid;
    }

    public void dfs(int node, int c, int[][] graph) {
        color[node] = c;
        int cNei = c == RED ? GREEN : RED;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == UNCOLORED) {
                dfs(neighbor, cNei, graph);
                if (!valid) {
                    return;
                }
            } else if (color[neighbor] != cNei) {
                valid = false;
                return;
            }
        }
    }
}

/***
 * 广度优先搜索
 * 时间复杂度：O(N+M)，其中 N 和 M 分别是无向图中的点数和边数。
 * 空间复杂度：O(N)，存储节点颜色的数组需要 O(N) 的空间，并且在广度优先搜索的过程中，队列中最多有 N-1 个节点，需要 O(N) 的空间。
 *
 */
class SolutionBfs {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n; ++i) {
            if (color[i] == UNCOLORED) {
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.offer(i);
                color[i] = RED;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    int cNei = color[node] == RED ? GREEN : RED;
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == UNCOLORED) {
                            queue.offer(neighbor);
                            color[neighbor] = cNei;
                        } else if (color[neighbor] != cNei) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
