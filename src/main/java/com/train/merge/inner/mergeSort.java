package com.train.merge.inner;

import com.train.sort.AbstractSort;
import com.train.sort.DataArray;

public class mergeSort extends AbstractSort {

	long[] result = new long[array.nElems + 1];

	public mergeSort() {
		array = new DataArray(20);

		for (int i = 0; i < 10; i++)
			array.insert((long) (java.lang.Math.random() * 10000));
		array.display();
	}

	private void merge(long[] workSpace, int lowBound, int mid, int upperBound) {

		int lowPtr = lowBound;
		int upperPtr = mid + 1;
		
		int j = 0;
		while (lowPtr <= mid && upperPtr <= upperBound) {
			if (this.array.a[lowPtr] < this.array.a[upperPtr]) {
				workSpace[j++] = this.array.a[lowPtr++];
			} else if (this.array.a[lowPtr] >= this.array.a[upperPtr]) {
				workSpace[j++] = this.array.a[upperPtr++];
			}
		}
		while (lowPtr <= mid) {
			workSpace[j++] = this.array.a[lowPtr++];
		}
		while (upperPtr <= upperBound) {
			workSpace[j++] = this.array.a[upperPtr++];
		}

		for (int k = 0; k < j ; k++){
			this.array.a[lowBound + k] = workSpace[k];
			
		}

		for (int k = 0; k < array.nElems; k++){
			if (k==lowBound){
				System.out.print("[ ");
			}
			System.out.print(" " + array.a[k]);
			if (k==lowBound+j-1){
				System.out.print(" ]");
			}
		}
		System.out.println();
	}

	// -----------------------------------------------------------

	public void mergeSortAction(long[] workSpace, int low, int high) {

		if (low == high) {
			return;
		} else {
			int mid = (high + low) / 2;
			mergeSortAction(workSpace, low, mid);
			mergeSortAction(workSpace, mid + 1, high);
			merge(workSpace, low, mid, high);
		}
	}

	@Override
	public void Sort() {

		long[] work = new long[array.nElems];
		mergeSortAction(work, 0, array.nElems - 1);

		// this.array.display();
	}

	// end class DArray

	public static void main(String[] args) {
		mergeSort sort = new mergeSort();
		sort.Sort();
	}
}
