package com.train.search;

public class interpolationSearch {
	private static long[] a; // ref to array a
	private int nElems; // number of data items

	// -----------------------------------------------------------

	// -----------------------------------------------------------
	public int size() {
		return nElems;
	}

	// -----------------------------------------------------------
	public int find(long searchKey) {
		// return recFind(searchKey, 0, nElems - 1);
		return recFindNoCallItSelf(searchKey, 0, nElems - 1);
	}

	// -----------------------------------------------------------
	private int recFindNoCallItSelf(long searchKey, int lowerBound,
			int upperBound) {
		int curIn;
		while (lowerBound < upperBound) {
			curIn =(int)( lowerBound + (searchKey - a[lowerBound])
					/ (a[upperBound] - a[lowerBound])
					* (upperBound - lowerBound));
			if (a[curIn] == searchKey)
				return curIn; // found it

			else // divide range
			{
				if (a[curIn] < searchKey) // it's in upper half
					lowerBound = curIn + 1;
				else
					// it's in lower half
					upperBound = curIn - 1;
			} // end else divide range
		}
		return 0;
	} // end recFind()

	// -----------------------------------------------------------

	// -----------------------------------------------------------

	public void insert(long value) // put element into array
	{
		int j;
		for (j = 0; j < nElems; j++)
			// find where it goes
			if (a[j] > value) // (linear search)
				break;
		for (int k = nElems; k > j; k--)
			// move bigger ones up
			a[k] = a[k - 1];
		a[j] = value; // insert it
		nElems++; // increment size
	} // end insert()
		// -----------------------------------------------------------

	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++)
			// for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}

	public static void main(String[] args) {
		int maxSize = 100; // array size
		interpolationSearch search = new interpolationSearch();
		a = new long[maxSize]; // create the array

		search.insert(72); // insert items
		search.insert(90);
		search.insert(45);
		search.insert(126);
		search.insert(54);
		search.insert(99);
		search.insert(144);
		search.insert(27);
		search.insert(135);
		search.insert(81);
		search.insert(18);
		search.insert(108);
		search.insert(9);
		search.insert(117);
		search.insert(63);
		search.insert(36);

		search.display(); // display array

		int searchKey = 27; // search for item
		if (search.find(searchKey) != search.size())
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can't find " + searchKey);
	} // end main()
} // end class BinarySearchApp
// //////////////////////////////////////////////////////////////
