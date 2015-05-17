package com.train.sort.simple;

import com.train.sort.AbstractSort;

public class SelectSort extends AbstractSort {

	public SelectSort() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Sort() {
		int size = this.array.nElems;
		for (int j = 0; j < size; j++) {
			int minIndex = j;
			for (int i = j+1; i<size;  i++) {
				if (this.array.a[i] < (this.array.a[minIndex])) {
					minIndex=i;
				}
			}
			if (minIndex!=j)
			this.array.swap(j, minIndex);
		}

	}

	public static void main(String[] args) {
		SelectSort sort = new SelectSort();
		sort.Sort();

	}

}
