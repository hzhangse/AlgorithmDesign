package com.train.sort.simple;

import com.train.sort.AbstractSort;
import com.train.sort.DataArray;
import com.train.sort.LongDataSort;

public class SelectSort  extends LongDataSort {


	@Override
	public void Sort() {
		int size = this.array.nElems;
		for (int j = 0; j < size; j++) {
			int minIndex = j;
			for (int i = j+1; i<size;  i++) {
				if (this.array.data[i] < (this.array.data[minIndex])) {
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
