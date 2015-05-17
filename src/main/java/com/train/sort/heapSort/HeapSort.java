package com.train.sort.heapSort;

import com.train.sort.AbstractSort;
import com.train.sort.DataArray;

public class HeapSort extends AbstractSort {

	public HeapSort(){
		array = new DataArray(20);
		array.insert(0);
		
		array.insert(3);array.insert(6);array.insert(9);array.insert(8);
		array.display();
	}
	public void buildHeap(DataArray array,int start,int end){
		long tmp = array.a[start];
		
		int j= start*2;
		while (j<=end){
			
			if (j+1<=end&&array.a[j]<array.a[j+1]){
				j++;
			}
			if (array.a[j]>tmp){
				array.a[start] = array.a[j]; 
			}else{
				
				break;
			}
			start = j; 
			j=start*2;
		}
		array.a[start] = tmp;
	}
	
	public void adjustHeap(DataArray array,int end){
		for (int i=end/2;i>0;i--){
			buildHeap(array,i,end);
		}
	}
	
	public void swap(DataArray array,int start, int end){
		long max = array.a[start];		
		array.a[start]=array.a[end];
		array.a[end]=max;
		System.out.println("heap data swap result");
		array.display();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.train.AbstractSort#Sort(com.train.DataArray)
	 */
	public void Sort(){
		int end = array.nElems-1;
		adjustHeap(array,end);
		System.out.println("init heap result:");
		array.display();
		for (int i=end;i>1;i--){
			//swap(array,1,i);
			array.swap(1, i);
			buildHeap(array,1,i-1);
			System.out.println("adjust heap result:");
			array.display();
			System.out.println("--------------");
		}	
	}
	
	
	public static void main(String[] args){		
		HeapSort sort = new HeapSort();
		sort.Sort();
		sort.array.display();
	}
}
