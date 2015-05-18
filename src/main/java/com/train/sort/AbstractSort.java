package com.train.sort;


public abstract class AbstractSort<T> {
	
	
	abstract public void init();
	
	public AbstractSort(){
		init();
	
	}
    protected DataArray<T> array;
	
	abstract public void Sort();
}
