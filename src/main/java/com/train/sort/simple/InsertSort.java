package com.train.sort.simple;

import com.train.sort.LongDataSort;

public class InsertSort extends LongDataSort {
	
		
	
	
	protected void insertSort(int increcement){
		int length = this.array.nElems;
		for (int i = increcement; i < length; i++) {
			long tmp = this.array.data[i];
			int j;
			for ( j = i - increcement; j >= 0; j=j-increcement) {
				if (tmp < this.array.data[j]) {
					this.array.data[j + increcement] = this.array.data[j];
				} else {					
					break;
				}
			}
			this.array.data[j + increcement]= tmp;
			this.array.display();
		}
	}
	@Override
	public void Sort() {
		insertSort(1);
	}

	public static void main(String[] args) {
		InsertSort sort = new InsertSort();
		sort.Sort();
		sort.array.display();

	}

}
