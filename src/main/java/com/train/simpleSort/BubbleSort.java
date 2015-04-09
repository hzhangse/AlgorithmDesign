/**
 * 
 */
package com.train.simpleSort;

import com.train.AbstractSort;

/**
 * @author hzhangse
 *
 */
public class BubbleSort extends AbstractSort {

	/**
	 * 
	 */
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
			for (int i = j; i<size-1;  i++) {
				if (this.array.a[i] > this.array.a[i+1]) {
					this.array.swap(i, i + 1);
				}
			}
		}

	}

	public static void main(String[] args){
		BubbleSort sort = new BubbleSort();
		sort.Sort();
	}
}
