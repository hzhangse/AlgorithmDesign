package com.train.graph.KeyPath;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.train.graph.KeyPath.Node;
import com.train.graph.KeyPath.Vertex;

public class KeyPathGraphNew {

	private Vertex[] vertexs;// 节点数组，用来保存结点
	private LinkedList<Node>[] adjTab;// 存取图链表
	private int pos = -1;
	private Stack<Integer> stack;// 栈
	private Stack<Integer> restack;// 栈的备份

	private int vertexNum;// 结点数
	private Node start;
	private int edgeCount;// 记录边的个数
	private int kNum;// 记录关键活动的数量

	private int[] ve;// 事件的最早发生时间
	private int[] vl;// 事件的最迟发生时间
	private int[] e;// 活动的最早发生时间
	private int[] l;// 活动的最晚发生时间
	private int[] key;// 用来存储关键路径

	/**
	 * 构造函数，用来初始化一个图
	 * 
	 * @param size
	 *            图的结点数量
	 */

	public KeyPathGraphNew(int size) {
		vertexNum = size;
		vertexs = new Vertex[size];
		adjTab = new LinkedList[size];
		stack = new Stack<Integer>();
		restack = new Stack<Integer>();

		for (int i = 0; i < size; i++) {
			adjTab[i] = new LinkedList<Node>();
		}
		ve = new int[size];
		vl = new int[size];
		for (int d = 0; d < size; d++) {
			vl[d] = -1;
		}
		edgeCount = 0;
	}

	/**
	 * 增加结点
	 * 
	 * @param obj
	 *            结点的名称
	 */
	void add(Object obj) {
		/**
		 * 所添加的结点个数必须小于所申请的结点空间
		 */
		assert pos < vertexs.length;
		vertexs[++pos] = new Vertex(obj);
	}

	/**
	 * 增加任务之间的依赖关系和任务运行时间
	 * 
	 * @param from
	 *            被依赖的事件
	 * @param to
	 *            依赖的事件
	 * @param weight
	 *            从被依赖的事件到到依赖的事件之间所要花费的时间
	 */
	void addEdge(int from, int to, int weight) {

		Node node = new Node(to, null, weight);
		adjTab[from].addLast(node);
		vertexs[to].in++;
		edgeCount++;
	}

	/**
	 * 根据拓扑排序算法判断图是有环的存在
	 * 
	 * @return 是否有环的存在
	 */

	boolean topo() {

		int count = 0;// 用来记录拓扑排序时，没有入度的结点数是否小于节点数

		/**
		 * 扫描顶点表，将入度为0的顶点压栈
		 */

		for (int i = 0; i < vertexNum; i++) {
			System.out.print(" vertexs[" + i + "] 入度：" + vertexs[i].in);
			Iterator<Node> iter = adjTab[i].iterator();
			System.out.print("    adjTable["+i+"]:");
			while (iter.hasNext()) {
				Node p = iter.next();
				int k = ((Integer) p.getData()).intValue();
				int weight= p.getWeight();
				System.out.print("  -->"+k+"("+weight+")");
			}
			System.out.println();
			if (vertexs[i].in == 0) {
				stack.push(i);
				System.out.println(" vertexs[" + i + "] 进栈");
			}
		}

		/**
		 * 如果栈不为空的话，则进行循环 退出栈顶元素，输出，累加器加1，将顶点的各个邻接点的入度减1，将新的入度为0的顶点入栈
		 * 
		 */

		while (!stack.isEmpty()) {
			/**
			 * 利用restack将栈的拓扑顺序进行备份
			 */
			restack.push(stack.peek());
			/**
			 * 取出栈顶元素，累加器加1
			 */
			int j = stack.pop();
			System.out.println("弹出：" + j+" 开始遍历 adjTable["+j+"]");
			count++;
			Iterator<Node> iter = adjTab[j].iterator();

			/**
			 * 记录当前的事件最早发生时间
			 */
			int preweight = ve[j];

			/**
			 * 将与此节点有关的点的入度减1，若入度减为0，则入栈
			 */
			while (iter.hasNext()) {
			
				Node p = iter.next();
				
				System.out.println("\n	deal with:" +p );
				int k = ((Integer) p.getData()).intValue();
				vertexs[k].in--;
				System.out.println("		vertexs[" + k + "].in :" + vertexs[k].in);
				if (vertexs[k].in == 0) {
					stack.push(k);
					System.out.println("		进栈：" + k);
				}
				int temp = ((Integer) p.getData()).intValue();

				/**
				 * 求事件的最早发生时间ve[k]
				 */

				/**
				 * 判断新得到的事件最早发生时间是否比原先的事件最早发生时间长 公式：ve[1] = 0; ve[k] =
				 * max{ve[j]+len<vj,vk>}
				 */
				if (p.getWeight() + preweight > ve[temp]) {
					ve[temp] = p.getWeight() + preweight;
					System.out.println("		ve[" + temp + "]=ve[" + j + "]+len["
							+ j + "," + k + "]=" + ve[temp]);
				}

			}

		}

		/**
		 * 如果得到的节点数量比原先的结点少，则证明有回路存在
		 */

		if (count < vertexNum) {

			System.out.println("有回路，无法得到关键路径！");
			return false;

		}
		System.out.print("ve array result:");
		for (int i = 0; i < vertexNum; i++)
			System.out.print(" ve[" + i + "]:" + ve[i]);
		System.out.println();
		return true;

	}

	public void calculate() {

		int s = 0;// 控制e（活动的最早发生时间）的增长
		int t = 0;// 控制l(活动的最迟发生时间)的增长

		/**
		 * 初始化活动的最早开始时间与最迟开始时间
		 */
		e = new int[edgeCount];
		l = new int[edgeCount];
		key = new int[edgeCount];

		/**
		 * 按逆拓扑有序来求其余各顶点的最迟发生时间
		 * 原理：restack里保存着拓扑排序的顺序数列，将从restack里将最后一个节点取出，即没有事件依赖于它的结点
		 * 取出的数放进backstack中,
		 * 再从restack里一个一个取出节点元素，看其是否跟已放入backstack中的元素相连，若有相连，则进行vl的计算，如此重复
		 * 计算公式：vl[k] = min{vl[j]-len<vk,vj>}
		 */

		for (int i = 0; i < vertexNum; i++) {
			vl[i] = ve[vertexNum - 1];
		}
		/**
		 * 当记录原拓扑顺序的restack不为空时，则进行循环
		 */
		while (!restack.isEmpty()) {

			/**
			 * 将已经比较完的结点放进backstack中，然后将restack里取出来的值与backstack里的值一一对比
			 */

			int q = restack.pop();
			System.out.println("popup:" + q+"开始遍历 adjTable["+q+"]");
			Iterator<Node> iter = adjTab[q].iterator();

			while (iter.hasNext()) {
				Node vertex = iter.next();
				int weight = vertex.getWeight();
				int k = ((Integer) vertex.getData()).intValue();
				System.out.println("	deal with:"+vertex);
				System.out.println("		orginal vl[" + q + "]=" + vl[q]);
				System.out.println("		vl[" + q + "]=vl[" + k + "]-len[" + q + ","
						+ k + "]=" + (vl[k] - weight));
				if (vl[k] - weight < vl[q]) {

					vl[q] = vl[k] - weight;
					System.out.println("		result :vl[" + q + "]=vl[" + k
							+ "]-len[" + q + "," + k + "]=" + vl[q]);
				}

			}

		}

		System.out.print("vl array result:");
		for (int i = 0; i < vertexNum; i++)
			System.out.print(" vl[" + i + "]:" + vl[i]);
		System.out.println();
		/**
		 * 求活动的最早开始时间e[i]与活动的最晚开始时间l[i]
		 * 若活动ai是由弧<Vk,Vj>表示，根据AOE网的性质，只有事件vk发生了，活动ai才能开始
		 * 。也就是说，活动ai的最早开始时间应等于事件vk的最早发生时间。 因此有e[i] = ve[k];
		 * 活动ai的最晚开始时间是指，在不推迟整个工期的前提下
		 * ，ai必须开始的最晚开始时间。若ai由有向边<vi,vj>表示，则ai的最晚开始时间要保证事件vj的最迟发生时间不拖后
		 * 因此，应该有l[i] = vl[j] - len<vk,vj>
		 */

		for (int h = 0; h < vertexNum; h++) {
			Iterator<Node> iter = adjTab[h].iterator();

			/**
			 * 查看所有当前节点的下一个结点
			 */
			while (iter.hasNext()) {
				Node begin = iter.next();
				e[s++] = ve[h];
				l[t++] = vl[((Integer) begin.getData()).intValue()]
						- begin.getWeight();

			}

		}

		kNum = 0;
		for (int w = 0; w < e.length; w++) {

			if (l[w] - e[w] <= 0) {
				key[kNum++] = w;
			}

		}
	}

	/**
	 * 
	 * @return 事件的最早开始时间
	 */
	public int[] getVE() {
		return ve;
	}

	/**
	 * 
	 * @return 事件的最迟开始时间
	 */
	public int[] getVl() {
		return vl;
	}

	/**
	 * @return 活动的最早开始时间
	 */
	public int[] getE() {
		return e;
	}

	/**
	 * 
	 * @return 活动的最晚开始时间
	 * 
	 */
	public int[] getL() {
		return l;
	}

	/**
	 * 
	 * @return 关键活动的点
	 */
	public int[] getKey() {
		return key;
	}

	/**
	 * 
	 * @return 关键活动的个数
	 */
	public int getKNum() {
		return kNum;
	}

	public static void main(String[] args) {
		/**
		 * 测试没有回路的情况
		 * 
		 */
		System.out.println("<!--------测试没有回路的情况-------------->");
		KeyPathGraphNew graph = new KeyPathGraphNew(9);
		graph.add("a");
		graph.add("b");
		graph.add("c");
		graph.add("d");
		graph.add("e");
		graph.add("f");
		graph.add("g");
		graph.add("h");
		graph.add("i");
		graph.addEdge(0, 1, 6);
		graph.addEdge(0, 2, 4);
		graph.addEdge(0, 3, 5);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 4, 1);
		graph.addEdge(3, 5, 2);
		graph.addEdge(4, 6, 9);
		graph.addEdge(4, 7, 7);
		graph.addEdge(5, 7, 4);
		graph.addEdge(6, 8, 2);
		graph.addEdge(7, 8, 4);
		if (graph.topo()) {
			graph.calculate();

			int[] e = graph.getE();
			int[] l = graph.getL();
			int[] key = graph.getKey();
			int[] ve = graph.getVE();
			int[] vl = graph.getVl();

			System.out.println("事件的最早发生时间：");
			for (int w = 0; w < ve.length; w++) {
				System.out.print(ve[w] + "      ");
			}
			System.out.println();

			System.out.println("事件的最晚发生时间：");
			for (int w = 0; w < vl.length; w++) {
				System.out.print(vl[w] + "      ");
			}
			System.out.println();

			System.out.println("活动的最早发生时间：");
			for (int w = 0; w < e.length; w++) {
				System.out.print(e[w] + "      ");
			}

			System.out.println();
			System.out.println("活动的最迟发生时间：");
			for (int w = 0; w < l.length; w++) {
				System.out.print(l[w] + "      ");
			}

			System.out.println();

			System.out.println("关键活动有：");
			for (int w = 0; w < graph.getKNum(); w++) {
				System.out.print(key[w] + "      ");
			}
			System.out.println();

			/**
			 * 测试有回路的情况
			 * 
			 */
			System.out.println("<!--------测试没有回路的情况-------------->");
			graph = new KeyPathGraphNew(9);
			graph.add("a");
			graph.add("b");
			graph.add("c");
			graph.add("d");
			graph.add("e");
			graph.add("f");
			graph.add("g");
			graph.add("h");
			graph.add("i");
			graph.addEdge(0, 1, 6);
			graph.addEdge(2, 0, 4);
			graph.addEdge(0, 3, 5);
			graph.addEdge(1, 4, 1);
			graph.addEdge(4, 2, 1);
			graph.addEdge(3, 5, 2);
			graph.addEdge(4, 6, 7);
			graph.addEdge(4, 7, 7);
			graph.addEdge(5, 7, 4);
			graph.addEdge(6, 8, 2);
			graph.addEdge(7, 8, 4);

			if (graph.topo()) {
				graph.calculate();

				int[] e1 = graph.getE();
				int[] l1 = graph.getL();
				int[] key1 = graph.getKey();
				int[] ve1 = graph.getVE();
				int[] vl1 = graph.getVl();

				System.out.println("事件的最早发生时间：");
				for (int w = 0; w < ve.length; w++) {
					System.out.print(ve[w] + "      ");
				}
				System.out.println();

				System.out.println("事件的最晚发生时间：");
				for (int w = 0; w < vl.length; w++) {
					System.out.print(vl[w] + "      ");
				}
				System.out.println();

				System.out.println("活动的最早发生时间：");
				for (int w = 0; w < e.length; w++) {
					System.out.print(e[w] + "      ");
				}

				System.out.println();
				System.out.println("活动的最迟发生时间：");
				for (int w = 0; w < l.length; w++) {
					System.out.print(l[w] + "      ");
				}

				System.out.println();

				System.out.println("关键活动有：");
				for (int w = 0; w < graph.getKNum(); w++) {
					System.out.print(key[w] + "      ");
				}
			}

		}

	}
}
