package com.train.sort.merge.inner;

import com.train.sort.LongDataSort;

public class mergeSort extends LongDataSort {

	long[] result = new long[array.nElems + 1];

	

	private void merge(long[] workSpace, int lowBound, int mid, int upperBound) {

		int lowPtr = lowBound;
		int upperPtr = mid + 1;
		
		int j = 0;
		while (lowPtr <= mid && upperPtr <= upperBound) {
			if (this.array.data[lowPtr] < this.array.data[upperPtr]) {
				workSpace[j++] = this.array.data[lowPtr++];
			} else if (this.array.data[lowPtr] >= this.array.data[upperPtr]) {
				workSpace[j++] = this.array.data[upperPtr++];
			}
		}
		while (lowPtr <= mid) {
			workSpace[j++] = this.array.data[lowPtr++];
		}
		while (upperPtr <= upperBound) {
			workSpace[j++] = this.array.data[upperPtr++];
		}

		for (int k = 0; k < j ; k++){
			this.array.data[lowBound + k] = workSpace[k];
			
		}

		for (int k = 0; k < array.nElems; k++){
			if (k==lowBound){
				System.out.print("[ ");
			}
			System.out.print(" " + array.data[k]);
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
