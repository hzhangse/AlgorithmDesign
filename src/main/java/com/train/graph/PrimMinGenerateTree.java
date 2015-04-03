package com.train.graph;

import java.util.Arrays;
import java.util.Scanner;

public class PrimMinGenerateTree extends MinGenerateTree {
	// near 数组每个记录表示 其他未到达节点 到 已选出的最优路径节点集 的 最佳方案。 比如near[3]=7,表示
	// 节点3到已选中节点（比如有，4，7）最短的方式是 3->7.
	// near [7]=0,表示当前节点7已经被选到最佳路径中了。无需再计算
	private int[] near = new int[MAX];
	private double cost[][] = new double[MAX][MAX];

	// 初始化
	public void init() {

	
		// 二维数组的填充要注意
		for (int i = 0; i < MAX; ++i) {
			Arrays.fill(cost[i], INFINITY);
		}

		Weight[] weights = new Weight[] { new Weight(0, 1, 10),
				new Weight(0, 5, 11), new Weight(1, 2, 18),
				new Weight(1, 8, 12), new Weight(1, 6, 16),
				new Weight(2, 3, 22), new Weight(2, 8, 8),
				new Weight(3, 8, 21), new Weight(3, 4, 20),
				new Weight(3, 6, 24), new Weight(3, 7, 16),
				new Weight(4, 5, 26), new Weight(4, 7, 7),
				new Weight(5, 6, 17), new Weight(6, 7, 19) };
		for (int i = 0; i < weights.length; i++) {
			cost[weights[i].col][weights[i].row] = weights[i].weight;
			cost[weights[i].row][weights[i].col] = weights[i].weight;
		}
		this.printGraph();

	}

	// 寻找最小成本的边
	public Edge getMinCostEdge() {
		Edge tmp = new Edge();
		double min = INFINITY;

		for (int i = 0; i < MAX; ++i) {
			for (int j = i + 1; j < MAX; ++j) {
				if (cost[i][j] < min) {
					min = cost[i][j];
					tmp.start = i;
					tmp.end = j;
					tmp.cost = cost[i][j];
				}
			}
		}
		// System.out.println(min);
		return tmp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.graph.MinGenerateTree#generate()
	 */
	public void generate() {
		int p, q, w;
		Edge tmp = getMinCostEdge();
		edge.add(tmp);
		p = tmp.start;
		q = tmp.end;
		mincost = cost[p][q];

		// near 数组记录了，到当前数组下标对应节点的最近节点，比如 near[i]=7, 表示到节点i 的最短路径节点是7
		for (int i = 0; i < MAX; ++i) {
			if (cost[i][p] < cost[i][q]) {
				near[i] = p;
			} else {
				near[i] = q;
			}
		}
		near[p] = near[q] = -1;
		// 找剩下的n-2条边
		for (int i = 1; i < MAX; i++) {
			double min = INFINITY;
			tmp = new Edge();
			// cost[j][near[j]] 表示 j 节点对应的最短路径
			// 遍历所有节点的最小路径，并筛选出其中的最小边。
			for (int j = 0; j < MAX; j++) {
				if (near[j] != -1 && cost[j][near[j]] < min) {
					tmp.start = j;
					tmp.end = near[j];
					tmp.cost = cost[j][near[j]];
					min = cost[j][near[j]];
				}
			}

			if (tmp.cost > 0) {
				edge.add(tmp);
				System.out.println(" start:" + tmp.start + "--- end:"
						+ tmp.end + " cost:" + tmp.cost);
				near[tmp.start] = -1;

				// 根据新找出的最短路径节点（tmp.start），重新计算near数组.
				for (int k = 0; k < MAX; k++) {
					if (near[k] != -1 && cost[k][near[k]] > cost[k][tmp.start]) {
						near[k] = tmp.start;
					}
				}
			}
		}
		if (mincost >= INFINITY) {
			System.out.println("no spanning tree");
		}
	}

	// 打印邻接矩阵
	public void printGraph() {
		System.out.println("-------------该临街矩阵如下:-----------");
		for (int i = 0; i < this.cost.length; i++) {
			for (int j = 0; j < this.cost[i].length; j++) {
				System.out.print(cost[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 打印结果
	public void print() {
		double total = 0f;
		for (int i = 0; i < edge.size(); ++i) {
			Edge e = edge.get(i);
			System.out.println("the " + (i + 1) + "th edge:" + e.start + "---"
					+ e.end + " cost:" + this.cost[e.start][e.end]);
			total = total + this.cost[e.start][e.end];
		}
		System.out.println("total cost:" + +total);
	}

	public static void main(String args[]) {
		PrimMinGenerateTree sp = new PrimMinGenerateTree();
		sp.init();
		sp.generate();
		sp.print();
	}
}
