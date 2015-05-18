package com.train.sort;

import java.lang.reflect.Array;


public class DataArray<T> {
	public T[] data; // ref to array a
	public int nElems; // number of data items
	private Class<T> type;

	// --------------------------------------------------------------

	T[] createArray(int size) {
		 return (T[]) Array.newInstance(type, size);    
	}

	public DataArray(Class<T> type,int max) // constructor
	{
		this.type = type;
		data = createArray(max);
		nElems = 0; // no items yet
	}



	// --------------------------------------------------------------
	public void insert(T value) // put element into array
	{
		data[nElems] = value; // insert it
		nElems++; // increment size
	}

	// --------------------------------------------------------------
	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(data[j] + " "); // display it
		System.out.println("");
	}

	// --------------------------------------------------------------
	public void swap(int one, int two) {
		System.out.println("swap data a[" + one + "]:" + data[one] + " with a["
				+ two + "]:" + data[two]);
		T temp = data[one];
		data[one] = data[two];
		data[two] = temp;
		
		System.out.println("result:");
		this.display();
		System.out.println("");
	}
	// --------------------------------------------------------------

	// --------------------------------------------------------------

}
