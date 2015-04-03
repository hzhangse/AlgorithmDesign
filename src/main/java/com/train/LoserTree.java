package com.train;

/*
 * ResultSet.java     0.0.1 2013/04/04 
 * Copyright(C) 2013 db-iir RUC. All rights reserved. 
 */
import java.util.ArrayList;

/**
 * This Class implements the loser tree algorithm.
 * 
 */
public class LoserTree {
	private int[] tree = null;// 以顺序存储方式保存所有非叶子结点
	private int size = 0;
	private ArrayList<Result> leaves = null;// 叶子节点

	public LoserTree(ArrayList<Result> initResults) {
		this.leaves = initResults;
		this.size = initResults.size();
		this.tree = new int[size];
		for (int i = 0; i < size; ++i) {
			tree[i] = -1;
		}
		for (int i = size - 1; i >= 0; --i) {
			adjust(i);
		}

	}

	public void del(int s) {
		leaves.remove(s);
		size--;
		tree = new int[size];
		for (int i = 0; i < size; ++i) {
			tree[i] = -1;
		}
		for (int i = size - 1; i >= 0; --i) {
			adjust(i);
		}

	}

	public void add(Result leaf, int s) {
		leaves.set(s, leaf);// 调整叶子结点
		adjust(s);// 调整非叶子结点
	}

	public Result getLeaf(int i) {
		return leaves.get(i);
	}

	public int getWinner() {
		return tree[0];
	}

	public void adjust(int s) {
		// s指向当前的值最小的叶子结点（胜者）
		int t = (s + size) / 2;// t是s的双亲

		while (t > 0) {

			if (s >= 0 && tree[t] == -1) {
				// 将树中的当前结点指向其子树中值最小的叶子
				int tmp = s;
				s = tree[t];
				tree[t] = tmp;
			}
			if (s >= 0 && leaves.get(s).compareTo(leaves.get(tree[t])) > 0) {
				// 将树中的当前结点指向其子树中值最小的叶子
				int tmp = s;
				s = tree[t];
				tree[t] = tmp;
			}

			t /= 2;
		}
		tree[0] = s;// 树根指向胜者
		for (int i = 0; i < tree.length; i++)
			System.out.print(tree[i] + " ");
		System.out.println();
		if (this.getWinner()!=-1&&leaves.get(this.getWinner()).getValue()!=-1) {
			System.out.println(leaves.get(tree[0]).getValue());			
		}
//		if (tree[0] == -1) {
//			System.out.println(leaves.get(tree[0]).a);
//			//leaves.get(tree[0]).a = -1;
//		}
	}

	public static void main(String[] args) {

		ArrayList<Result> initResults = new ArrayList<Result>();
		initResults.add(new Result(9));
		initResults.add(new Result(7));
		initResults.add(new Result(4));
		initResults.add(new Result(6));
		LoserTree tree = new LoserTree(initResults);
		tree.leaves.get(tree.getWinner()).setValue(-1);
		tree.adjust(tree.getWinner());
		tree.leaves.get(tree.getWinner()).setValue(-1);
		tree.adjust(tree.getWinner());
		tree.leaves.get(tree.getWinner()).setValue(-1);
		tree.adjust(tree.getWinner());
		//tree.adjust(tree.getWinner());
		
	}
}
