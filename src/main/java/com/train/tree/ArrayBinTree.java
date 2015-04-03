package com.train.tree;

public class ArrayBinTree<T> {
	private Object[] datas;
	private int DEFAULT_DEEP = 8;

	// 保存树的深度，默认为8
	private int deep;
	private int arraySize;

	public void init() {
		this.arraySize = (int) (Math.pow(2, deep) - 1);
		this.datas = new Object[this.arraySize];
	}

	// 默认的深度
	public ArrayBinTree() {
		this.deep = this.DEFAULT_DEEP;
		this.init();
	}

	// 指定深度
	public ArrayBinTree(int deep) {
		this.deep = deep;
		this.init();
	}

	// 指定深度和根节点
	public ArrayBinTree(int deep, T data) {
		this.deep = deep;
		this.init();
		this.datas[0] = data;
	}

	/**
	 * 为指定节点添加子节点
	 * 
	 * @param index
	 *            添加子节点的父节点的索引，由于数组从的索引从0开始，索引在计算上有点差别
	 * @param data
	 *            节点数据
	 * @param left
	 *            是否为左节点
	 */
	public void add(int index, T data, boolean left) {
		if (this.datas[index] == null) {
			throw new RuntimeException(index + "节点为空");
		}

		if (2 * index + 1 >= this.arraySize) {
			throw new RuntimeException("树底层已满");
		}

		if (left) {
			this.datas[2 * index + 1] = data;
		} else {
			this.datas[2 * index + 2] = data;
		}
	}

	public boolean empty() {
		return this.datas[0] == null;
	}

	@SuppressWarnings("unchecked")
	public T getRoot() {
		if (this.empty()) {
			return (T) this.datas[0];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T getParent(int index) {
		if (index >= 0 && index < this.arraySize) {
			return (T) this.datas[index / 2 - 1];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T getLeft(int index) {
		if (2 * index + 1 >= this.arraySize) {
			throw new RuntimeException("该节点为叶子节点，无子节点");
		}

		return (T) this.datas[2 * index + 1];
	}

	@SuppressWarnings("unchecked")
	public T getRight(int index) {
		if (2 * index + 2 >= this.arraySize) {
			throw new RuntimeException("该节点为叶子节点，无子节点");
		}

		return (T) this.datas[2 * index + 2];
	}

	public int deep() {
		return this.deep;
	}

	// 获取指定节点的位置
	public int position(T data) {
		for (int i = 0; i < this.arraySize; i++) {
			if (this.datas[i].equals(data)) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public String toString() {
		return java.util.Arrays.toString(this.datas);
	}

	public static void main(String[] args) {
		ArrayBinTree<String> tree = new ArrayBinTree<String>(4, "root");
		tree.add(0, "0_left", true);
		tree.add(0, "0_right", false);

		tree.add(1, "1_left", true);
		tree.add(1, "1_right", false);

		tree.add(2, "2_left", true);
		tree.add(2, "2_right", false);
		System.out.println(tree);
	}

}
