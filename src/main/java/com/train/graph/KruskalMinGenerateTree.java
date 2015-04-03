package com.train.graph;

/*
*
*功能:无向图最小生成树Kruskal算法实现案例
*/
import java.util.ArrayList;

public class KruskalMinGenerateTree extends MinGenerateTree{
	
	
	private ArrayList<Edge> target = new ArrayList<Edge>();//目标边，最小生成树
	private int[] parent = new int[MAX];//标志所在的集合
	
	
	
	
	public KruskalMinGenerateTree(){}
	
	
	//初始化
	public void init(){
		
	
		Weight[] weights = new Weight[] { new Weight(0, 1, 10),
				new Weight(0, 5, 11), new Weight(1, 2, 18),
				new Weight(1, 8, 12), new Weight(1, 6, 16),
				new Weight(2, 3, 22), new Weight(2, 8, 8),
				new Weight(3, 8, 21), new Weight(3, 4, 20),
				new Weight(3, 6, 24), new Weight(3, 7, 16),
				new Weight(4, 5, 26), new Weight(4, 7, 7),
				new Weight(5, 6, 17), new Weight(6, 7, 19) };
		for (int i = 0; i < weights.length; i++) {
			Edge e = new Edge();
			e.start = weights[i].col;
			e.end = weights[i].row;
			e.cost = weights[i].weight;
			edge.add(e);
			
		}
		
		
		
		for(int i = 0; i < MAX; ++i){
			parent[i] = -1;
		}
	}
	
	
	public int find(int[] parent, int k){
		while (parent[k]>-1){
			k= parent[k];
		}
		return k;
	}
	/*
	 * (non-Javadoc)
	 * @see com.train.graph.MinGenerateTree#generate()
	 */
	public void generate(){
		//找剩下的n-2条边
		
		while(edge.size() > 0){
			//每次取一最小边，并删除
			double min = INFINITY;
		
			Edge tmp = null;
			for(int j = 0; j < edge.size(); ++j){
				Edge tt = edge.get(j);
				if(tt.cost < min){
					min = tt.cost;
					tmp = tt;
				}
			}
			System.out.println(" the edge:" + tmp.start + "---" + tmp.end+
					" cost:" + tmp.cost);
			int jj = find(parent,tmp.start);
			int kk = find(parent,tmp.end);
			//去掉环
			if(jj != kk){
				
				target.add(tmp);
				parent[jj]=kk;
				
			}
			for (int i=0;i<parent.length;i++){
				System.out.print(" " + parent[i]);
			}
			System.out.println();
			edge.remove(tmp);
		}
		
	}
	//打印结果
	public void print(){
		double total = 0d;
		for(int i = 0; i < target.size(); ++i){
			Edge e = target.get(i);
			System.out.println("the " + (i+1) + "th edge:" + e.start + "---" + e.end+
					" cost:" + e.cost);
			total = total +e.cost;
		}
		System.out.println("total cost:"+
				 + total);
	}
	
	public static void main(String args[]){
		KruskalMinGenerateTree sp = new KruskalMinGenerateTree();
		sp.init();
		sp.generate();
		sp.print();
	}
}

