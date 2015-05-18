package com.train.sort.quickSort;

import com.train.sort.DataArray;
import com.train.sort.LongDataSort;

public class quickSort extends   LongDataSort  {

	public static void main(String[] args) {
		quickSort sort = new quickSort();
		sort.Sort();

	}

	public int partition(DataArray<Long> array, int start, int end) {
		long tmp = array.data[start];
		int left = start;
		int right = end;

		while (left <right) {
			while (left <right&&array.data[right] >= tmp) {
				right--;
			}
			array.swap(right,left);
			
			while (left <right&&array.data[left] <=tmp) {
				left++;
			}
			array.swap(left, right);

			
		}
		return left;
	}

	@Override
	public void Sort() {
		QSort(array,1,array.nElems-1);
	}

	public void QSort(DataArray<Long> array, int start, int end) {
		if (start < end) {
			int pivot;
			pivot = partition(array, start, end);
			System.out.println("pivot:"+ pivot);
			array.display();
			System.out.println();
			QSort(array, 0, pivot - 1);
			QSort(array, pivot + 1, end);
		}
	}

}
