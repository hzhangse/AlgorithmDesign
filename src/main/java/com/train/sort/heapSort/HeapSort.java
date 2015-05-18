package com.train.sort.heapSort;

import com.train.sort.DataArray;
import com.train.sort.LongDataSort;

public class HeapSort extends  LongDataSort  {

	

	public void init(){
		array = new DataArray<Long>(Long.class,10);
		array.insert(0L);
		array.insert(8L);
		array.insert(3L);
		array.insert(6L);
		array.insert(9L);
		
		array.display();
	}
	
	public HeapSort() {
		
	}

	public void buildHeap(int start, int end) {
		long tmp = array.data[start];

		int j = start * 2;
		while (j <= end) {

			if (j + 1 <= end && array.data[j] < array.data[j + 1]) {
				j++;
			}
			if (array.data[j] > tmp) {
				array.data[start] = array.data[j];
			} else {

				break;
			}
			start = j;
			j = start * 2;
		}
		array.data[start] = tmp;
	}

	public void adjustHeap( int end) {
		for (int i = end / 2; i > 0; i--) {
			buildHeap( i, end);
		}
	}

	public void swap( int start, int end) {
		long max = array.data[start];
		array.data[start] = array.data[end];
		array.data[end] = max;
		System.out.println("heap data swap result");
		array.display();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.AbstractSort#Sort(com.train.DataArray)
	 */
	public void Sort() {
		int end = array.nElems - 1;
		adjustHeap(end);
		System.out.println("init heap result:");
		array.display();
		for (int i = end; i > 1; i--) {
			// swap(array,1,i);
			array.swap(1, i);
			buildHeap(1, i - 1);
			System.out.println("adjust heap result:");
			array.display();
			System.out.println("--------------");
		}
	}

	public static void main(String[] args) {
		HeapSort sort = new HeapSort();
		sort.Sort();
		sort.array.display();
	}
}
