package com.train.sort;

public abstract class LongDataSort extends AbstractSort<Long> {

	public void init(){
		array = new DataArray<Long>(Long.class,10);
		for (int i=0;i<10;i++)
		array.insert((long)(java.lang.Math.random()*10000));
		array.display();
	}
	

}
