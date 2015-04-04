package com.train.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DijKstraTree extends MinGenerateTree {
	private double[] near = new double[MAX];
	private double cost[][] = new double[MAX][MAX];
	private int[] parent = new int[MAX];// 标志所在的集合

	// 初始化
	public void init() {

		// 二维数组的填充要注意
		for (int i = 0; i < MAX; ++i) {
			Arrays.fill(cost[i], INFINITY);
		}

		Weight[] weights = new Weight[] { new Weight(0, 1, 1),
				new Weight(0, 2, 5), new Weight(1, 2, 3), new Weight(1, 3, 7),
				new Weight(1, 4, 5), new Weight(2, 4, 1), new Weight(2, 5, 7),
				new Weight(3, 4, 2), new Weight(3, 6, 3), new Weight(4, 6, 6),
				new Weight(4, 5, 3), new Weight(4, 7, 9), new Weight(5, 7, 5),
				new Weight(6, 7, 2), new Weight(6, 8, 7), new Weight(7, 8, 4), };
		for (int i = 0; i < weights.length; i++) {
			cost[weights[i].col][weights[i].row] = weights[i].weight;
			cost[weights[i].row][weights[i].col] = weights[i].weight;
		}
		this.printGraph();

		for (int i = 0; i < MAX; ++i) {
			near[i] = INFINITY;
			parent[i] = 0;
		}
	}

	public void generate() {
		
		for (int i = 1; i < MAX; i++) {

			near[i] = cost[0][i];
		
			
			for (int j = 1; j < MAX; j++) {

				if (j != i && cost[j][i] < INFINITY && near[j] < INFINITY) {
					double tmp = near[j] + cost[j][i];
					if (tmp < near[i]) {
						near[i] = tmp;

						parent[i] = j;
					}
				}

			}
			for (int j = 1; j < MAX; j++) {
				if (i != j && near[j] < INFINITY) {
					if (near[j] > near[i] + cost[j][i]) {
						near[j] = near[i] + cost[j][i];
						parent[j] = i;
					}
				}
			}

		}
		for (int i = 1; i < MAX; i++) {
			String parentStr = i+ " < -- ";
			int parentid = i;
			while (parent[parentid] > 0) {
				
				parentStr = parentStr + parent[parentid]+" <-- ";
				parentid = parent[parentid];
			}
			System.out.println("a[0][" + i + "] length:" + near[i] + " , Path:"
					+ parentStr+0);
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
		DijKstraTree sp = new DijKstraTree();
		sp.init();
		sp.generate();
		
	}
}
