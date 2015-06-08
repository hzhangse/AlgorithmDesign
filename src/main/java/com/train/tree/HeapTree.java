package com.train.tree;

import java.util.ArrayList;

public class HeapTree {

	private ArrayList<Result> leaves = null;// 叶子节点

	public HeapTree(ArrayList<Result> initResults) {
		this.leaves = initResults;

	}

	public void buildHeapLeave(int start, int end) {
		buildHeapLeave(start, end, false);

	}

	// adjust min element is on top if inverse =true
	public void buildHeapLeave(int start, int end, boolean inverse) {
		Result tmp = new Result(leaves.get(start).getValue());
		
		int j = start * 2 + 1;

		while (j <= end) {
			if (j + 1 <= end
					&& leaves.get(j + 1).compareTo(leaves.get(j), inverse) < 0) {
				j = j + 1;
			}
			if (leaves.get(j).compareTo(tmp, inverse) < 0) {

				leaves.get(start).setValue(leaves.get(j).getValue());
			}else{
				break;
			}
			start = j;
			j = start * 2 + 1;
		}
		leaves.get(start).setValue(tmp.getValue());
	}

	public void adjustHeap(int end) {
		adjustHeap(end, false);
	}

	public void adjustHeap(int end, boolean inverse) {
		for (int i = end / 2; i >= 0; i--) {
			buildHeapLeave(i, end, inverse);
		}
		System.out.println("adjust heap result:");
		this.display();
	}

	public void swap(int start, int end) {
		int tmp = leaves.get(start).getValue();
		leaves.get(start).setValue(leaves.get(end).getValue());
		leaves.get(end).setValue(tmp);
		System.out.println("heap data swap result");
		display();
	}

	public Result getTop() {
		return this.leaves.get(1);
	}

	public void display() // displays array contents
	{
		for (int j = 0; j < leaves.size(); j++)
			// for each element,
			System.out.print(leaves.get(j).getValue() + " "); // display it
		System.out.println("");

		 CommonTree tree = new CommonTree(leaves.toArray());
		 tree.displayTree();
	}

	public ArrayList<Result> getLeaves() {
		return leaves;
	}

	public void setLeaves(ArrayList<Result> leaves) {
		this.leaves = leaves;
	}

	public void Sort() {
		Sort(false);
	}

	public void Sort(boolean inverse) {
		int end = this.getLeaves().size() - 1;
		adjustHeap(end, inverse);
		for (int i = end; i > 0; i--) {
			swap(0, i);
			adjustHeap(i - 1, inverse);
		}
		System.out.println("sortted heap tree: ");
		display();
	}

	public void Sort(int heapsize) {
		int end = heapsize - 1;
		adjustHeap(end);
		for (int i = end; i > 0; i--) {
			swap(1, i);
			adjustHeap(i - 1);
		}
		System.out.println("sortted heap tree: ");
		display();
	}

	public void Sort(int heapsize, boolean inverse) {
		int end = heapsize - 1;
		adjustHeap(end);
		for (int i = end; i > 0; i--) {
			swap(1, i);
			adjustHeap(i - 1);
		}
		System.out.println("sortted heap tree: ");
		display();
	}

	public static void main(String[] args) {
		ArrayList<Result> initResults = new ArrayList<Result>();
		// for (int i = 5; i > 0; i--) {
		// initResults.add(new Result(i + 1));
		// }
		// for (int i = 0; i < 5; i++) {
		// initResults.add(new Result(i + 1));
		// }
		initResults.add(new Result(3));
		initResults.add(new Result(6));
		initResults.add(new Result(3));
		initResults.add(new Result(7));
		initResults.add(new Result(2));
		HeapTree tree = new HeapTree(initResults);
		tree.Sort(true);

	}

}
