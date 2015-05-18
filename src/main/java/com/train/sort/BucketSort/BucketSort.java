package com.train.sort.BucketSort;

import com.train.sort.AbstractSort;
import com.train.sort.DataArray;

public class BucketSort extends AbstractSort<Integer> {

	int[] buckets;

	@Override
	public void init() {
		array = new DataArray<Integer>(Integer.class, 20);
		array.insert(0);
		array.insert(2);
		array.insert(3);
		array.insert(6);
		array.insert(2);
		array.insert(9);
		array.display();

	}

	public static void main(String[] args) {

		BucketSort sort = new BucketSort();
		sort.Sort();
	}

	public BucketSort() {

	}

	public void bucketSort() {
		
		
		// buckets用于记录待排序元素的信息
		// buckets数组定义了max-min个桶

		// 计算每个元素在序列出现的次数
		for (int i = 0; i < this.array.nElems; i++) {
			buckets[array.data[i]]++;
		}
		// 计算“落入”各桶内的元素在有序序列中的位置
		for (int i = 1; i < buckets.length; i++) {
			buckets[i] = buckets[i] + buckets[i - 1];
		}
		// 将data中的元素完全复制到tmp数组中
		Integer[] tmp = new Integer[this.array.data.length];
		
		// 根据buckets数组中的信息将待排序列的各元素放入相应位置
		for (int k = this.array.nElems - 1; k >= 0; k--) {
			tmp[--buckets[this.array.data[k]]] = this.array.data[k];
		}
		
		 System.arraycopy(tmp, 0, this.array.data, 0, this.array.data.length);
		 this.array.display();
	}

	public void Sort() {
		int max = 9;
		buckets = new int[max+1];
		this.bucketSort();
	}

}
