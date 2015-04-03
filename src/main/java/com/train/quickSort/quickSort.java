package com.train.quickSort;

import com.train.AbstractSort;
import com.train.DataArray;

public class quickSort extends AbstractSort {

	public static void main(String[] args) {
		quickSort sort = new quickSort();
		sort.Sort();

	}

	public int partition(DataArray array, int start, int end) {
		long tmp = array.a[start];
		int left = start;
		int right = end;

		while (left <right) {
			while (left <right&&array.a[right] >= tmp) {
				right--;
			}
			array.swap(right,left);
			
			while (left <right&&array.a[left] <=tmp) {
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

	public void QSort(DataArray array, int start, int end) {
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
