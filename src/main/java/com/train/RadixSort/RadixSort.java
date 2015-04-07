package com.train.RadixSort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSort {

	// 基于计数排序的基数排序算法
	private static void radixSort(int[] array, int radix, int distance) {
		// array为待排序数组
		// radix，代表基数
		// 代表排序元素的位数

		int length = array.length;
		int[] temp = new int[length];// 用于暂存元素
		int[] count = new int[radix];// 用于计数排序
		int divide = 1;

		for (int i = 0; i < distance; i++) {

			System.arraycopy(array, 0, temp, 0, length);
			Arrays.fill(count, 0);

			for (int j = 0; j < length; j++) {
				int tempKey = (temp[j] / divide) % radix;
				count[tempKey]++;
			}

			for (int j = 1; j < radix; j++) {
				count[j] = count[j] + count[j - 1]; // count[j]
													// 的值代表基数为j的元素将放置的下标最大位置+1
			}

			// 个人觉的运用计数排序实现计数排序的重点在下面这个方法
			for (int j = array.length-1 ; j >=0; j--) {// 倒序遍历，保证按照已经排好的顺序的元素，先放置到后面的位置
				int tempKey = (temp[j] / divide) % radix;// 取当前元素基数
				count[tempKey]--; // 递减count index，准备开始存放
				array[count[tempKey]] = temp[j];// 存放
			}

			for (int k = 0; k < array.length; k++) {
				System.out.print("  " + array[k]);
			}
			System.out.println();
			divide = divide * radix;

		}

	}

	private static void radixSortUsingQueque(int[] array, int radix,
			int distance) {
		// array为待排序数组
		// radix，代表基数
		// 代表排序元素的位数

		int length = array.length;

		int divide = 1;
		Queue<Integer>[] queue = new LinkedList[radix];

		for (int i = 0; i < radix; i++) {
			queue[i] = new LinkedList<Integer>();
		}

		for (int i = 0; i < distance; i++) {
			for (int j = 0; j < length; j++) {
				int tempKey = (array[j] / divide) % radix;
				queue[tempKey].offer(array[j]);
			}
			
			int k=0;
			for (int j = 0; j < radix; j++) {
				while (!queue[j].isEmpty()){
					Integer ele = queue[j].poll();
					array[k++] = ele;
				}
			}
			divide = divide * radix;

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] array = { 3, 2, 3, 2, 5, 333, 45566, 2345678, 78, 990, 12, 432,
				56 };
		//radixSort(array, 10, 7);
		radixSortUsingQueque(array, 10, 7);
		for (int i = 0; i < array.length; i++) {
			System.out.print("  " + array[i]);
		}

	}

}
