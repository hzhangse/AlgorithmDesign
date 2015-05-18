package com.train.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * find the shortest path
 * 
 * @author hzhangse
 * 
 */
public class DijKstraTree extends MinGenerateTree {
	private double[] near = new double[MAX];
	private boolean[] finished = new boolean[MAX];
	private double cost[][] = new double[MAX][MAX];
	private int[] parent = new int[MAX];// 标志所在的集合
	// private ArrayList<Edge> edgeList = new ArrayList<Edge>();// 目标边
	private Map<Integer, ArrayList<Edge>> pathMap = new HashMap<Integer, ArrayList<Edge>>();// 目标边

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

	/**
	 * computer the shortest path from node 0 to node n
	 */
	public void generate() {

		parent[0] = -1;
		for (int i = 1; i < MAX; i++) {
			near[i] = cost[0][i];
			parent[i] = 0;
		}
		
		// k used to keep time of computer shortest path from each node （1..MAX）to start node （0）
		for (int k = 1; k < MAX; k++) {
			
			int minIndex = -1;
			double min = INFINITY;
			// get the index of node(minIndex) which is the shortest path start form node
			// 0
			for (int i = 1; i < MAX; i++) {
				if (!finished[i] && near[i] < min) {
					minIndex = i;
					min = near[i];

				}
			}
			// to iterator the parent of minIndex and set the path graph and print here
			if (minIndex != -1) {
				finished[minIndex] = true;
				ArrayList<Edge> lst = new ArrayList<Edge>();// 目标边
				int index = minIndex;
				int parentIndex = parent[index];
				while (parentIndex >= 0) {
					Edge edge = new Edge();
					edge.start = parentIndex;
					edge.end = index;
					edge.cost = cost[parentIndex][index];
					index = parentIndex;
					parentIndex = parent[index];

					lst.add(edge);
				}

				pathMap.put(minIndex, lst);
				printpath();

			}

			//use current minIndex to compute shortest path that covered from 0 to each unreached node 
			for (int i = 1; i < MAX; i++) {
				if (!finished[i] && cost[minIndex][i] < INFINITY) {
					double tmp = near[minIndex] + cost[minIndex][i];
					if (tmp < near[i]) {
						near[i] = tmp;
						parent[i] = minIndex;
					}
				}
			}

		}

		for (int i = 1; i < MAX; i++) {
			String parentStr = i + " < -- ";
			int parentid = i;
			while (parent[parentid] > 0) {

				parentStr = parentStr + parent[parentid] + " <-- ";
				parentid = parent[parentid];
			}
			System.out.println("a[0][" + i + "] length:" + near[i] + " , Path:"
					+ parentStr + 0);
		}
	}

	public void printpath() {
		System.out.println("-------------min path-----------");
		for (Integer key : pathMap.keySet()) {
			List<Edge> lst = pathMap.get(key);
			double total = 0d;
			for (Edge edg : lst) {
				total = total + edg.cost;
			}
			System.out.println("v0>>v" + key + " path:" + lst + " total:"
					+ total);

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
