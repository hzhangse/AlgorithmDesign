package com.train.search;

import java.util.Stack;

public class FibonacciSearch {

	private static long[] a; // ref to array a
	private int nElems; // number of data items

	private long Fibonacci(int items) {
		Long a = new Long(1);
		Long b = new Long(1);
		long result = 0;
		// 创建堆栈
		Stack<Long> stack = new Stack<Long>();
		stack.push(a);
		// 向堆栈中添加元素
		stack.push(b);
		// 向堆栈中添加元素
		int i = 1;
		// 循环变量

		// 求斐波纳契数的哪一项
		if (items == 1 || items == 2) {
			// 当项是1和2时，输出1
			System.out.println(1);
		} else if (items > 2) {
			// 当项大于2时，输出对应项的结果
			while (i <= items - 2) {
				// 去除items是1和2的项，所以items-2
				a = stack.pop();
				// 取出堆栈中栈顶的元素
				b = stack.pop();
				// 取出堆栈中栈顶的元素
				Long z = a + b;
				// 计算从堆栈中取出元素的和
				stack.push(a);
				// 将先从堆栈中取出的元素放入堆栈中
				stack.push(z);
				// 将将计算结果放入堆栈中
				i++;
			}
			// 输出所求项的值
			result = stack.pop();
			System.out.println("斐波纳契数第" + items + "项的值是：" + result);

		}
		return result;
	}

	// -----------------------------------------------------------
	public int size() {
		return nElems;
	}

	// -----------------------------------------------------------
	public int find(long searchKey) {
		// return recFind(searchKey, 0, nElems - 1);
		return recFindNoCallItSelf(searchKey, 0, nElems - 1);
	}

	// -----------------------------------------------------------
	private int recFindNoCallItSelf(long searchKey, int lowerBound,
			int upperBound) {
		int curIn;
		int k = 0;
		while (Fibonacci(k) - 1 < upperBound) {
			k++;
		}
		for (int i = upperBound; i < Fibonacci(k) - 1; i++) {
			this.insert(a[upperBound - 1]);
		}
		while (lowerBound <= upperBound) {
			curIn = lowerBound + (int) Fibonacci(k - 1) - 1;
			if (a[curIn] == searchKey){
				return curIn; // found it
			}
			else // divide range
			{
				if (a[curIn] < searchKey) {// it's in upper half
					lowerBound = curIn + 1;
					k=k-2;
				} else {
					// it's in lower half
					upperBound = curIn - 1;
					k=k-1;
				}
			} // end else divide range
		}
		return 0;
	} // end recFind()

	// -----------------------------------------------------------

	// -----------------------------------------------------------

	public void insert(long value) // put element into array
	{
		int j;
		for (j = 0; j < nElems; j++)
			// find where it goes
			if (a[j] > value) // (linear search)
				break;
		for (int k = nElems; k > j; k--)
			// move bigger ones up
			a[k] = a[k - 1];
		a[j] = value; // insert it
		nElems++; // increment size
	} // end insert()
		// -----------------------------------------------------------

	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}

	public static void main(String[] args) {
		int maxSize = 100; // array size
		FibonacciSearch search = new FibonacciSearch();
		a = new long[maxSize]; // create the array

		search.insert(72); // insert items
		search.insert(90);
		search.insert(45);
		search.insert(126);
		search.insert(54);
		search.insert(99);
		search.insert(144);
		search.insert(27);
		search.insert(135);
		search.insert(81);
		search.insert(18);
		search.insert(108);
		search.insert(9);
		search.insert(117);
		search.insert(63);
		search.insert(36);

		search.display(); // display array

		int searchKey = 27; // search for item
		if (search.find(searchKey) != search.size())
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can't find " + searchKey);
	} // end main()
} // end class BinarySearchApp
// //////////////////////////////////////////////////////////////
