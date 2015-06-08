package com.train.tree;


public class CommonTreeNode<T> {
	public T data;
	public CommonTreeNode<T> leftChild; // this node's left child
	public CommonTreeNode<T> rightChild; // this node's right child
	public CommonTreeNode<T> parent;

	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(data);
		System.out.print("} ");
	}
/*
 * (non-Javadoc)
 * @see java.lang.Object#toString()
 */
	public String toString(){
		return data.toString();
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public CommonTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(CommonTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public CommonTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(CommonTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public CommonTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(CommonTreeNode<T> parent) {
		this.parent = parent;
	}

	
}
