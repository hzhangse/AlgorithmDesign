package com.train.simpleSort;

// bubbleSort.java
// demonstrates bubble sort
// to run this program: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
class DataArray {
	public long[] a; // ref to array a
	public int nElems; // number of data items

	// --------------------------------------------------------------

	public DataArray(int max) // constructor
	{
		a = new long[max]; // create the array
		nElems = 0; // no items yet
	}

	// --------------------------------------------------------------
	public void insert(long value) // put element into array
	{
		a[nElems] = value; // insert it
		nElems++; // increment size
	}

	// --------------------------------------------------------------
	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}

	// --------------------------------------------------------------
	public void swap(int one, int two) {
		long temp = a[one];
		a[one] = a[two];
		a[two] = temp;
		System.out.println("swap data a["+one+"]:"+a[one]+" with a["+two+"]:"+a[two]);
	}
	// --------------------------------------------------------------

	// --------------------------------------------------------------

	// --------------------------------------------------------------
} // end class ArrayBub
// //////////////////////////////////////////////////////////////

public class BubbleSortApp {
	public static void bubbleSort() {
		DataArray data = initArr();
		System.out.println("Start bubble sort");
		int out, in;
		for (out = data.nElems - 1; out > 1; out--)
			// outer loop (backward)
			for (in = 0; in < out; in++)
				// inner loop (forward)
				if (data.a[in] > data.a[in + 1]) {// out of order?
					
					data.swap(in, in + 1); 
					data.display(); 
					}// swap them
				else{
					
				}
	}

	public static void selectSort() {
		DataArray data = initArr();
		System.out.println("Start select sort");
		int out, in;
		int minIndex = 0;
		
		for (out = 0; out < data.nElems; out++) {
			minIndex = out;
			for (in = out + 1; in < data.nElems; in++) {
				// inner loop (forward)
				if (data.a[minIndex] > data.a[in]) // out of order?
				{
					minIndex = in;
					
				}
			}
			if (out!=minIndex){
				data.swap(out, minIndex);
				data.display(); 
			}
		}
	}
	
	public static void insertSort() {
		DataArray data = initArr();
		System.out.println("Start insert sort");
		int out, in;
		int minIndex = 0;
		
		for (out = 1; out < data.nElems; out++) {
			
			long tmp = data.a[out];
			System.out.println("out:"+out+" " +tmp );
			
			in = out - 1;
			while (in>=0){
				if (tmp < data.a[in]) // out of order?
				{

					data.a[in+1]= data.a[in];
					data.display();
					in--;
				}else{
					break;
				}
				
			}
			
			
			data.a[in+1]=tmp;
			data.display();
		}
	}


	public static DataArray initArr(){
		int maxSize = 100; // array size
		 
		 DataArray arr = new DataArray(maxSize); // create the array

		arr.insert(77); // insert 10 items
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		arr.display(); // display items
		return arr;
	}
	
	public static void main(String[] args) {
		
	    
		

		bubbleSort(); // bubble sort them
		System.out.println("--------------------------------");
		//selectSort();
		System.out.println("--------------------------------");
		//insertSort();
		//arr.display(); // display them again
	} // end main()
} // end class BubbleSortApp
// //////////////////////////////////////////////////////////////
