package com.train;

public abstract class AbstractSort {
	public AbstractSort(){
		array = new DataArray(20);
		array.insert(0);
		for (int i=0;i<4;i++)
		array.insert((long)(java.lang.Math.random()*10000));
		array.display();
	}
    protected DataArray array;
	
	abstract public void Sort();
}
