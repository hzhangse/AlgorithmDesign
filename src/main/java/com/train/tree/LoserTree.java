package com.train.tree;

/*
 * Zhang Hong
 * 
 */
import java.util.ArrayList;

/**
 * This Class implements the loser tree algorithm.
 * 
 */
public class LoserTree {

	class LoserTreeNode<T> {
		int index;
		T value;

		LoserTreeNode(int index, T value) {
			this.index = index;
			this.value = value;
		}

		public String toString() {
			if (value.toString().equalsIgnoreCase("-1"))
				return "-1";
			return "tree[" + index + "]:" + value;
		}
	}

	private Integer[] tree = null;// 以顺序存储方式保存所有非叶子结点
	private int size = 0;
	private ArrayList<Result> leaves = null;// 叶子节点

	public LoserTree(ArrayList<Result> initResults) {
		this.leaves = initResults;
		this.size = initResults.size();
		this.tree = new Integer[size];
		for (int i = 0; i < size; ++i) {
			tree[i] = -1;// -1 means max value
		}
		// adjust from the end leave node to 0
		for (int i = size - 1; i >= 0; --i) {
			adjust(i);
		}

	}

	public void del(int s) {
		leaves.remove(s);
		size--;
		tree = new Integer[size];
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

	public void adjust(int current) {
		// s指向当前的值最小的叶子结点（胜者）
		int parent = (current + size) / 2;// t是s的双亲

		while (parent > 0) {

			if (current >= 0 && tree[parent] == -1) {
				// 将树中的当前结点指向其子树中值最小的叶子
				int tmp = current;
				current = tree[parent];
				tree[parent] = tmp;
			}
			if (current >= 0 && leaves.get(current).compareTo(leaves.get(tree[parent])) > 0) {
				// 将树中的当前结点指向其子树中值最小的叶子
				int tmp = current;
				current = tree[parent];
				tree[parent] = tmp;
			}

			parent /= 2;
		}
		tree[0] = current;// 树根指向胜者
		// for (int i = 0; i < tree.length; i++)
		// System.out.print(tree[i] + " ");
		// System.out.println();
		display();
		if (this.getWinner() != -1
				&& leaves.get(this.getWinner()).getValue() != -1) {
			System.out.println("the winner is tree["+tree[0]+"]:"
					+ leaves.get(tree[0]).getValue());
		}
		// if (tree[0] == -1) {
		// System.out.println(leaves.get(tree[0]).a);
		// //leaves.get(tree[0]).a = -1;
		// }
	}

	public void display() // displays array contents
	{
		LoserTreeNode<Result>[] losetreeArr =  new LoserTreeNode[tree.length-1];
		for (int j = 0; j < tree.length; j++) {
			
			System.out.print(tree[j] + " "); // display it
		}
		for (int j = 0; j < tree.length-1; j++) {
			Result result = new Result(tree[j+1]);
			if (result.getValue()!=-1){
				result = this.leaves.get(result.getValue());
			}
			LoserTreeNode<Result> node = new LoserTreeNode<Result>(tree[j+1],result);
			losetreeArr[j] = node;
			
			
		}
		System.out.println("");

		CommonTree losetree = new CommonTree(losetreeArr);
		losetree.displayTree();
	}

	public static void main(String[] args) {

		ArrayList<Result> initResults = new ArrayList<Result>();
		initResults.add(new Result(9));
		initResults.add(new Result(7));
		initResults.add(new Result(4));
		initResults.add(new Result(6));
		initResults.add(new Result(5));
		LoserTree tree = new LoserTree(initResults);
		// tree.leaves.get(tree.getWinner()).setValue(-1);
		// tree.adjust(tree.getWinner());
		// tree.leaves.get(tree.getWinner()).setValue(-1);
		// tree.adjust(tree.getWinner());
		// tree.leaves.get(tree.getWinner()).setValue(-1);
		// tree.adjust(tree.getWinner());
		// tree.adjust(tree.getWinner());

	}
}
