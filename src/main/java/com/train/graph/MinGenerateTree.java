package com.train.graph;

/*
 *功能:无向图最小生成树Prim算法实现案例
 */
import java.util.ArrayList;
import java.util.Scanner;

//插入的边的类

public class MinGenerateTree {
	protected static int MAX = 9;	
	protected ArrayList<Edge> edge = new ArrayList<Edge>();
	protected double INFINITY = 100;// 定义无穷大
	protected double mincost = 0.0;// 最小成本
	

	public MinGenerateTree() {
	}

	

	class Weight {

		int row; // 起点
		int col; // 终点
		int weight; // 权值

		Weight(int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}

	// 初始化
	public void init() {
		Scanner scan = new Scanner(System.in);
		int p,q;
		double w;
		
		System.out.println("spanning tree begin!Input the node number:");
		//n = scan.nextInt();
		System.out.println("Input the graph(-1,-1,-1 to exit)");
		
		while(true){
			p = scan.nextInt();
			q = scan.nextInt();
			w = scan.nextDouble();
			if(p < 0 || q < 0 || w < 0){
				break;
			}
			Edge e = new Edge();
			e.start = p;
			e.end = q;
			e.cost = w;
			edge.add(e);
		}
		
		mincost = 0.0;
	}

	
	public void generate(){
		
	}
	
	
}

 class Edge
{
	public int start;//始边
	public int end;//终边
	public double cost;//权重
}