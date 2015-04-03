package com.train.shellSort;

import com.train.AbstractSort;

public class ShellSort extends AbstractSort {

	
	public void insertSort(int increcement){
		System.out.println("increment :" + increcement);
		for (int i=increcement+1;i<this.array.nElems;i++){
			long tmp = this.array.a[i];
			int j= 0;
			for (j= i-increcement;  j>0;j= j - increcement){
				if (this.array.a[j]>tmp){
					this.array.a[j+increcement] = 	this.array.a[j];
					
				}else{
					break;
				}
				
			}
			this.array.a[j+increcement] = tmp;
		}
		array.display();
	}
	@Override
	public void Sort() {
		int h = this.array.nElems-1;
		do {
			 h= h/3+1;
			 
			 insertSort(h);
		}while (h>1);

	}

	public static void main(String[] args) {
		
		ShellSort sort = new ShellSort();
		sort.Sort();
		sort.array.display();
	}

}
