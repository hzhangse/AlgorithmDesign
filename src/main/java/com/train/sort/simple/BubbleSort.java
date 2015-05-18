/**
 * 
 */
package com.train.sort.simple;

import com.train.sort.LongDataSort;

/**
 * @author hzhangse
 *
 */
public class BubbleSort extends LongDataSort {

	
	public BubbleSort() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.AbstractSort#Sort()
	 */
	@Override
	public void Sort() {
		int size = this.array.nElems;
		for (int j = 0; j < size; j++) {
			
			for (int i = size-1; i>=j+1;  i--) {
				if (this.array.data[i] < this.array.data[i-1]) {
					this.array.swap(i, i - 1);
				}
			}
			System.out.println("outer loop "+j+":"+this.array.data[j]);
		}

	}

	public static void main(String[] args){
		BubbleSort sort = new BubbleSort();
		sort.Sort();
	}
}
