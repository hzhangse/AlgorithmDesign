package com.train.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CommonTree <T>{
	T[] data;
	public CommonTree (T[] data){
		this.data = data;
	}
	public  CommonTreeNode<T> createTee() // displays array contents
	{
		int nElems = data.length;
		
		CommonTreeNode<T> node = null;
		CommonTreeNode<T>  root = new CommonTreeNode<T>();;
		Queue<CommonTreeNode<T>> globalStack = new LinkedList<CommonTreeNode<T>>();
		root.setData(data[0]);
		globalStack.offer(root);
		int j=0;
		while (globalStack.isEmpty() == false && j< nElems/2) {
			node = globalStack.poll();
			
			if (j * 2 + 1 < nElems) {
				CommonTreeNode<T> lnode = new CommonTreeNode<T>();
				lnode.setData(data[j * 2 + 1]);
				node.setLeftChild(lnode);
				lnode.setParent(node);
				globalStack.offer(lnode);
			}
			if (j * 2 + 2 < nElems) {
				CommonTreeNode<T> rnode = new CommonTreeNode<T>();
				rnode.setData(data[j * 2 + 2]);
				node.setRightChild(rnode);
				rnode.setParent(node);
				globalStack.offer(rnode);
			}

			j++;
		}
		
//		for (int j = 0; j < nElems / 2; j++) {
//			
//			if (j==0){
//				
//				
//			}
//			
//			
//		}
		return root;
	}
	
	public void displayTree() {
		CommonTreeNode root = createTee();
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out
				.println("......................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				CommonTreeNode temp = (CommonTreeNode) globalStack.pop();
				if (temp != null) {
					System.out.print("[" + temp + "]");
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out
				.println("......................................................");
	} // end displayTree()

}
