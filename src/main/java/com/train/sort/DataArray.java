package com.train.sort;

public class DataArray {
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
		System.out.println("result:" );
		this.display();
		System.out.println("" );
	}
	// --------------------------------------------------------------

	// --------------------------------------------------------------


}
