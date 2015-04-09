package com.train.simpleSort;

import com.train.AbstractSort;

public class InsertSort extends AbstractSort {
	
	
	
	public void init(){
		size =10;
	}
	protected void insertSort(int increcement){
		int length = this.array.nElems;
		for (int i = 1+increcement; i < length; i++) {
			long tmp = this.array.a[i];
			int j;
			for ( j = i - increcement; j >= 0; j=j-increcement) {
				if (tmp < this.array.a[j]) {
					this.array.a[j + increcement] = this.array.a[j];
				} else {					
					break;
				}
			}
			this.array.a[j + increcement]= tmp;
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
