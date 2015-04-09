package com.train.tree;

import java.util.Stack;

public class BalanceBinaryTree extends BinaryTree {
	private final static int LH = 1;
	private final static int RH = -1;
	private final static int EH = 0;
	// TreeNode root;
	boolean taller;

	class BalanceNode extends TreeNode {

		BalanceNode() {
			iData = -1;// -1 means the node have not been set on tree
		}

		public int bf; // 节点平衡因子

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return iData + "," + bf;
		}

	} // end class Node

	public void adjustRelation(TreeNode root, TreeNode adjustNode) {
		TreeNode rootParent = root.parent;
		if (rootParent != null) {
			if (rootParent.leftChild == root) {
				rootParent.leftChild = adjustNode;

			} else if (rootParent.rightChild == root) {
				rootParent.rightChild = adjustNode;
			}

		}
		adjustNode.parent = rootParent;
		root.parent = adjustNode;
	}

	/* 对以root为根的二叉排序树作右旋处理， */
	/* 处理之后root指向新的树根结点，即旋转处理之前的左子树的根结点 */
	public TreeNode R_Rotate(TreeNode root) {
		TreeNode lchild = root.leftChild;
		TreeNode lrchild = lchild.rightChild;
		lchild.rightChild = root;
		root.leftChild = lrchild;
		adjustRelation(root, lchild);
		lrchild.parent = root;

		// this.root = lchild;
		return lchild;
	}

	/* 对以root为根的二叉排序树作左旋处理， */
	/* 处理之后root指向新的树根结点，即旋转处理之前的右子树的根结点0 */
	public TreeNode L_Rotate(TreeNode root) {
		TreeNode rchild = root.rightChild;
		TreeNode rlchild = rchild.leftChild;
		rchild.leftChild = root;
		root.rightChild = rlchild;
		adjustRelation(root, rchild);
		rlchild.parent = root;
		// this.root = rchild;
		return rchild;
	}

	/* 对以指针root所指结点为根的二叉树作左平衡旋转处理 */
	/* 本算法结束时，指针T指向新的根结点 */
	public void L_Balance(TreeNode root) {
		BalanceNode L = (BalanceNode) root.leftChild;
		switch (L.bf) {
		case LH: {
			((BalanceNode) root).bf = L.bf = EH;
			this.R_Rotate(root);
			break;
		}
		case RH: {
			BalanceNode Lr = (BalanceNode) L.rightChild;
			this.L_Rotate(L);
			this.R_Rotate(root);
			Lr.bf = EH;
			switch (Lr.bf) {
			case LH: {
				((BalanceNode) root).bf = RH;
				L.bf = EH;
				break;
			}
			case RH: {
				((BalanceNode) root).bf = EH;
				L.bf = LH;
				break;
			}
			case EH: {
				((BalanceNode) root).bf = L.bf = EH;
				break;
			}
			}
			break;
		}

		}

	}

	public void R_Balance(TreeNode root) {

		BalanceNode R = (BalanceNode) root.rightChild;
		switch (R.bf) {
		case RH: {
			((BalanceNode) root).bf = R.bf = EH;
			root = this.L_Rotate(root);
			break;
		}
		case LH: {
			BalanceNode Rl = (BalanceNode) R.leftChild;
			switch (Rl.bf) {
			case LH: {
				((BalanceNode) root).bf = EH;
				R.bf = RH;
				break;
			}
			case RH: {
				((BalanceNode) root).bf = LH;
				R.bf = EH;
				break;
			}
			case EH: {
				((BalanceNode) root).bf = R.bf = EH;
				break;
			}

			}
			Rl.bf = EH;
			this.R_Rotate(R);
			root = this.L_Rotate(root);
			break;

		}

		}

	}

	public boolean insertAVL(TreeNode rootNode, int keyword) {

		if (rootNode.iData == -1) {

			((BalanceNode) rootNode).bf = EH;
			rootNode.leftChild = new BalanceNode();
			rootNode.leftChild.parent = rootNode;
			rootNode.rightChild = new BalanceNode();
			rootNode.rightChild.parent = rootNode;
			rootNode.iData = keyword;

			taller = true;

		} else {

			// start at root
			if (keyword > rootNode.iData) {

				if (!insertAVL(rootNode.rightChild, keyword)) {
					return false;
				}

				if (taller) {
					switch (((BalanceNode) rootNode).bf) {
					case LH: {
						((BalanceNode) rootNode).bf = EH;
						taller = false;
						break;
					}
					case EH: {
						((BalanceNode) rootNode).bf = RH;
						taller = true;
						break;
					}
					case RH: {
						taller = false;
						this.R_Balance(rootNode);
						break;
					}
					}
				}
			} else if (keyword < rootNode.iData) {

				if (!insertAVL(rootNode.leftChild, keyword)) {
					return false;
				}
				if (taller) {
					switch (((BalanceNode) rootNode).bf) {
					case LH: {

						this.L_Balance(rootNode);
						taller = false;
						break;
					}
					case EH: {
						((BalanceNode) rootNode).bf = LH;
						taller = true;
						break;
					}
					case RH: {
						((BalanceNode) rootNode).bf = EH;
						taller = false;
						break;
					}
					}
				}
			} else {
				taller = false;
				System.out.println(keyword + " Node already exist!");

			}
		}

		return true;
	}

	public TreeNode getRoot() {
		while (this.root.parent != null) {
			this.root = root.parent;
		}
		return this.root;
	}

	public void displayTree(TreeNode root) {
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
				TreeNode temp = (TreeNode) globalStack.pop();
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BalanceBinaryTree tree = new BalanceBinaryTree();
		tree.root = tree.new BalanceNode();
		int a[] = { 1, 3, 5, 8, 7, 4, };
		for (int j = 0; j < a.length; j++) {
			tree.insertAVL(tree.getRoot(), a[j]);
			tree.displayTree(tree.getRoot());
		}

	}

}
