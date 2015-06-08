package com.train.sort;

import java.lang.reflect.Array;
import java.util.Stack;

import com.train.tree.CommonTreeNode;

public class DataArray<T> {
	public T[] data; // ref to array a
	public int nElems; // number of data items
	private Class<T> type;

	// --------------------------------------------------------------

	T[] createArray(int size) {
		return (T[]) Array.newInstance(type, size);
	}

	public DataArray(Class<T> type, int max) // constructor
	{
		this.type = type;
		data = createArray(max);
		nElems = 0; // no items yet
	}

	// --------------------------------------------------------------
	public void insert(T value) // put element into array
	{
		data[nElems] = value; // insert it
		nElems++; // increment size
	}

	// --------------------------------------------------------------
	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(data[j] + " "); // display it
		System.out.println("");
	}

	// --------------------------------------------------------------
	public void swap(int one, int two) {
		System.out.println("swap data a[" + one + "]:" + data[one] + " with a["
				+ two + "]:" + data[two]);
		T temp = data[one];
		data[one] = data[two];
		data[two] = temp;

		System.out.println("result:");
		this.display();
		System.out.println("");
	}

	// --------------------------------------------------------------

	// --------------------------------------------------------------

	private CommonTreeNode createTee() // displays array contents
	{
		CommonTreeNode root = null;
		for (int j = 0; j < nElems / 2; j++) {
			CommonTreeNode<T> node = new CommonTreeNode<T>();
			if (j==0){
				root = node;
			}
			node.setData(data[j]);

			if (j * 2 + 1 < nElems) {
				CommonTreeNode<T> lnode = new CommonTreeNode<T>();
				lnode.setData(data[j * 2 + 1]);
				node.setLeftChild(lnode);
				lnode.setParent(node);
			}
			if (j * 2 + 1 < nElems) {
				CommonTreeNode<T> rnode = new CommonTreeNode<T>();
				rnode.setData(data[j * 2 + 2]);
				node.setRightChild(rnode);
				rnode.setParent(node);
			}

		}
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
