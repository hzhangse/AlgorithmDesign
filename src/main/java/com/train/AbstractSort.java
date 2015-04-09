package com.train;

public abstract class AbstractSort {
	protected int size ;
	
	public void init(){
		size =7;
	}
	public AbstractSort(){
		init();
		array = new DataArray(20);
		array.insert(0);
		for (int i=0;i<size;i++)
		array.insert((long)(java.lang.Math.random()*10000));
		array.display();
	}
    protected DataArray array;
	
	abstract public void Sort();
}
