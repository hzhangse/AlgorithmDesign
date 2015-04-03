package com.train.tree;

public class Result implements Comparable<Result> {
	private Integer value;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Result(Integer a){
		this.value = a;
	}
	public int compareTo(Result o) {
		int result = 0;
		if (o.value ==-1){
			o.value = Integer.MAX_VALUE;
		}
		if (this.value ==-1){
			this.value = Integer.MAX_VALUE;
		}
		result= this.value.compareTo(o.value);
		if (o.value ==Integer.MAX_VALUE){
			o.value = -1;
		}
		if (this.value ==Integer.MAX_VALUE){
			this.value = -1;
		}
		return result;
	}

}
