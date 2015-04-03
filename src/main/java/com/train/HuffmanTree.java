package com.train;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

class Node<T> implements Comparable<Node<T>> {
	private T data;
	private int weight;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	boolean isleaf;
	private Code code;

	public boolean isIsleaf() {
		return isleaf;
	}

	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node(T data, int weight, boolean isleaf) {
		this.data = data;
		this.weight = weight;
		this.isleaf = isleaf;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "data:" + this.data + ";weight:" + this.weight
				+ (this.isleaf ? ";code:" + this.code.toString() : "");
	}

	public int compareTo(Node<T> other) {
		if (other.getWeight() > this.getWeight()) {
			return 1;
		}
		if (other.getWeight() < this.getWeight()) {
			return -1;
		}

		return 0;
	}
}

// 哈夫曼编码类
class Code {

	int[] bit; // 编码的数组
	int start; // 编码的开始下标
	int weight; // 权值

	Code(int n) {
		bit = new int[n];
		start = n - 1;
	}

	@Override
	public String toString() {
		int i = 0;
		String result = "";
		for (int j = start; j < bit.length; j++) {
			result = result + bit[j];
		}

		return result;
	}

}

public class HuffmanTree<T> {
	static int nodeNum;

	public static <T> Node<T> createTree(List<Node<T>> nodes) {
		nodeNum = nodes.size();
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node<T> left = nodes.get(nodes.size() - 1);
			Node<T> right = nodes.get(nodes.size() - 2);
			Node<T> parent = new Node<T>(null, left.getWeight()
					+ right.getWeight(), false);
			left.setParent(parent);
			right.setParent(parent);
			parent.setLeft(left);
			parent.setRight(right);
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	public static <T> List<Node<T>> breadth(Node<T> root) {
		List<Node<T>> list = new ArrayList<Node<T>>();
		Queue<Node<T>> queue = new ArrayDeque<Node<T>>();

		if (root != null) {
			queue.offer(root);
		}

		while (!queue.isEmpty()) {
			list.add(queue.peek());
			Node<T> node = queue.poll();

			if (node.getLeft() != null) {
				queue.offer(node.getLeft());
			}

			if (node.getRight() != null) {
				queue.offer(node.getRight());
			}
		}
		return list;
	}

	// 哈弗曼编码算法
	public static <T> void haffmanCode(List<Node<T>> nodes) {
		int n = nodeNum;
		Code code = new Code(n);// 4
		Node<T> child, parent;

		for (int i = 0; i < nodes.size(); i++)// 给前面n个输入的节点进行编码
		{
			child = nodes.get(i);// 0
			if (child.isleaf) {
				Node<T> leafNode = child;
				code.start = n - 1;// 3
				code.weight = child.getWeight();// 1

				parent = child.getParent();
				// 从叶子节点向上走来生成编码，画图即可。
				while (parent != null) {
					if (parent.getLeft() == child) {
						code.bit[code.start] = 0;
					} else {
						code.bit[code.start] = 1;
					}

					code.start--;

					child = parent;
					parent = child.getParent();
				}

				Code temp = new Code(n);
				for (int j = code.start + 1; j < n; j++) {
					temp.bit[j] = code.bit[j];
				}
				temp.weight = code.weight;
				temp.start = code.start;
				leafNode.setCode(temp);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Node<String>> list = new ArrayList<Node<String>>();
		list.add(new Node<String>("a", 7, true));
		list.add(new Node<String>("b", 5, true));
		list.add(new Node<String>("c", 4, true));
		list.add(new Node<String>("d", 2, true));

		Node<String> root = HuffmanTree.createTree(list);
		List<Node<String>> nodes = HuffmanTree.breadth(root);
		HuffmanTree.haffmanCode(nodes);
		System.out.println(nodes);
		
	}

}
