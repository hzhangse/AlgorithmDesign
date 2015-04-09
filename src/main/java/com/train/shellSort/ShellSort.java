package com.train.shellSort;

import com.train.simpleSort.InsertSort;

public class ShellSort extends InsertSort {

	@Override
	public void Sort() {
		int h = this.array.nElems;
		do {
			h = h / 3 + 1;
			insertSort(h);
		} while (h > 1);

	}

	public static void main(String[] args) {

		ShellSort sort = new ShellSort();
		sort.Sort();
		sort.array.display();
	}

}
