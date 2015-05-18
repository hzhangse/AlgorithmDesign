package com.train.sort.merge.outer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.train.tree.HeapTree;
import com.train.tree.LoserTree;
import com.train.tree.Result;

public class AdvancedMutiWayMergeSort extends BaseMergeSort {

	@Override
	public File merge(ArrayList<File> list) throws IOException {
		int fileSize = list.size();

		if (fileSize == 1) {
			return list.get(0);
		}

		ArrayList<DataInputStream> dinlist = new ArrayList<DataInputStream>();

		int[] ext = new int[fileSize];// 比较数组

		// File output = new File("multipleMerged");
		FileOutputStream os = new FileOutputStream(RESULT_FILE);
		BufferedOutputStream bout = new BufferedOutputStream(os);
		DataOutputStream dout = new DataOutputStream(bout);

		for (int i = 0; i < fileSize; i++) {
			try {
				dinlist.add(i, new DataInputStream(new BufferedInputStream(
						new FileInputStream(list.get(i)), BUFFER_SIZE)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int index = 0;

		for (int i = 0; i < fileSize; i++) {
			try {
				ext[i] = dinlist.get(i).readInt();
			} catch (Exception e) {
				System.err.println("file_" + i + "为空");
				ext[i] = -1;
			}
		}
		int count = fileSize;
		int[] sum = new int[fileSize];
		ArrayList<Result> initResults = new ArrayList<Result>();
		for (int i = 0; i < ext.length; i++) {
			initResults.add(new Result(ext[i]));
		}

		//Using lose tree
		LoserTree tree = new LoserTree(initResults);
		while (count > 0) {

			index = tree.getWinner();

			dout.writeInt(ext[index]);
			sum[index]++;
			try {
				ext[index] = dinlist.get(index).readInt();
			} catch (Exception e) {
				ext[index] = -1;
				count--;
				dinlist.get(index).close();
				// System.err.println(index + "空,写进:" +sum[index]);

			}
			tree.getLeaf(index).setValue(ext[index]);
			tree.adjust(index);
		}

		dout.close();
		return null;
	}

	/*
	 * split main file using heap tree
	 * 
	 * @see com.train.merge.outer.BaseMergeSort#split(java.io.File)
	 */
	protected ArrayList<File> split(File file) throws IOException {
		ArrayList<File> files = new ArrayList<File>();
		int[] buffer = new int[BUFFER_SIZE];
		FileInputStream fr = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(fr, BUFFER_SIZE);
		DataInputStream din = new DataInputStream(bin);
		boolean fileComplete = false;

		ArrayList<Result> initResults = new ArrayList<Result>();
		initResults.add(new Result(-1));// heap array must start form index =1
		for (int i = 0; i < FILE_COUNT && !fileComplete; i++) {
			try {
				initResults.add(new Result(din.readInt()));
			} catch (Exception e) {
				fileComplete = true;

			}
		}

		// 借助堆，读取排序的分割文件
		HeapTree tree = new HeapTree(initResults);
		int heapSize = tree.getLeaves().size() - 1;
		tree.adjustHeap(heapSize, true);
		int index = 0;
		while (!fileComplete && heapSize > 0) {

			try {

				Result input = new Result(din.readInt());
				int heapMin = tree.getTop().getValue();
				buffer[index++] = heapMin;
				tree.getTop().setValue(input.getValue());
				// if 当前读入值小于已取出的堆顶（最小值）, 则不处理当前这个读入值，并把它交换到堆的尾节点去
				// 随着类似情况逐渐发生，堆的规模将会虽小到0
				if (input.getValue().compareTo(heapMin) < 0) {
					tree.swap(1, heapSize);
					heapSize--;
				}
				if (heapSize > 0)
					tree.adjustHeap(heapSize, true);
			} catch (Exception e) {
				fileComplete = true;

			}
			// 当堆规模缩小到0时，把之前排序过的元素序列输出到一个分割文件去，并用那些占用堆空间的元素重jian堆。
			if (heapSize == 0 && !fileComplete) {
				File f = writeSplitSortedFile(index, buffer);
				files.add(f);
				index = 0;

				heapSize = tree.getLeaves().size() - 1;
				tree.adjustHeap(heapSize, true);
			} else if (fileComplete) {
				// at first, output the sorted elements that stored in buffer.
				if (index > 0) {
					File f = writeSplitSortedFile(index, buffer);
					files.add(f);
				}// secondly, output the left elements which is located in heap
					// tree. it was related about heap sort
				if (heapSize > 0) {
					index = 0;
					heapSize = tree.getLeaves().size() - 1;

					while (heapSize > 0) {
						tree.adjustHeap(heapSize, true);
						int heapMin = tree.getTop().getValue();
						buffer[index++] = heapMin;
						tree.getTop().setValue(
								tree.getLeaves().get(heapSize).getValue());
						heapSize--;
					}
					File f = writeSplitSortedFile(index, buffer);
					files.add(f);
				}
			}
		}

		din.close();
		bin.close();
		fr.close();
		return files;
	}

	public static void main(String[] args) throws IOException {

		AdvancedMutiWayMergeSort sort = new AdvancedMutiWayMergeSort();
		sort.createBigMainFile(MAIN_FILE);

		long start = System.currentTimeMillis();
		sort.sort(MAIN_FILE);

		long end = System.currentTimeMillis();

		recordFile((end - start) / 1000, true);
		System.out.println("cost:" + (end - start) + "ms");
		System.out.println("Merge Result:");
		DataInputStream input = new DataInputStream(new BufferedInputStream(
				new FileInputStream(RESULT_FILE), BUFFER_SIZE));
		while (true) {
			try {
				int x = input.readInt();
				System.out.print(" " + x);
			} catch (Exception e) {
				break;
			}

		}
		input.close();
	}
}
