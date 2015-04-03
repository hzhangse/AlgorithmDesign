package com.train.towers;

import java.util.Stack;

public class StackRecursionDemo {
	static void  a(){
		Long a = new Long(1);
		Long b = new Long(1);
		// 创建堆栈
		Stack<Long> stack = new Stack<Long>();
		stack.push(a);
		// 向堆栈中添加元素
		stack.push(b);
		// 向堆栈中添加元素
		int i = 1;
		// 循环变量
		int items = 6;
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
			System.out.println("斐波纳契数第" + items + "项的值是：" + stack.pop());
		}
	
	}
	
	static void b(){
		
		// 创建堆栈
		Stack<Integer> stack = new Stack<Integer>();
		
		// 向堆栈中添加元素
		stack.push(1);
		Integer i = 1;
		// 循环变量
		Integer items = 4;
		// 求斐波纳契数的哪一项
		if (items == 1) {
			// 当项是1和2时，输出1
			stack.push(items);
			System.out.println(1);
		} else if (items > 1) {
			// 当项大于2时，输出对应项的结果
			while (i <= items ) {
				// 去除items是1和2的项，所以items-2
				Integer a = stack.pop();
				
				// 取出堆栈中栈顶的元素
				Integer z = a *i;
				// 计算从堆栈中取出元素的和
				//stack.push(a);
				// 将先从堆栈中取出的元素放入堆栈中
				stack.push(z);
				// 将将计算结果放入堆栈中
				i++;
			}
			// 输出所求项的值
			System.out.println("斐波纳契数第" + items + "项的值是：" + stack.pop());
		}
	
	}
	public static void main(String[] args) {
	b();
	}
}