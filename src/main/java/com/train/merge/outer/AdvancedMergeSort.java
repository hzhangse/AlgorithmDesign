package com.train.merge.outer;

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

/**
 * use heapTree to split sorted file, use loserTree to merge splitted file .
 * 
 * @author hzhangse
 *
 */
public class AdvancedMergeSort extends MergeSort {
	private static int initHeapSize = 5;

	/*
	 * split main file using heap tree
	 * 
	 * @see com.train.merge.outer.MergeSort#split(java.io.File)
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
				if (input.getValue().compareTo(heapMin) >= 0) {

				} else {
					tree.swap(1, heapSize);
					heapSize--;
				}
				if (heapSize > 0)
					tree.adjustHeap(heapSize, true);
			} catch (Exception e) {
				fileComplete = true;

			}
			if (heapSize == 0 && !fileComplete) {
				File f = writeSplitSortedFile(index, buffer);
				files.add(f);
				index = 0;

				heapSize = tree.getLeaves().size() - 1;
				tree.adjustHeap(heapSize, true);
			} else if (fileComplete) {
				if (index > 0) {
					File f = writeSplitSortedFile(index, buffer);
					files.add(f);
				}
				if (heapSize > 0) {
					index = 0;
					heapSize = tree.getLeaves().size() - 1;
					
					while(heapSize>0) {
						tree.adjustHeap(heapSize, true);
						int heapMin = tree.getTop().getValue();
						buffer[index++] = heapMin;
						tree.getTop().setValue(tree.getLeaves().get(heapSize).getValue());
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

	/**
	 * ByLoserTree
	 * 
	 * @param list
	 * @throws IOException
	 */
	protected void multipleMerge(ArrayList<File> list) throws IOException {

		int fileSize = list.size();

		if (fileSize == 1) {
			return;
		}

		ArrayList<DataInputStream> dinlist = new ArrayList<DataInputStream>();

		int[] ext = new int[fileSize];// 比较数组

		// File output = new File("multipleMerged");
		FileOutputStream os = new FileOutputStream(MAIN_FILE);
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.train.merge.outer.MergeSort#mSort(java.io.File)
	 */
	public void mSort(File file) throws IOException {
		super.mSort(file);

	}

	public static void main(String[] args) throws IOException {

		Random random = new Random(System.currentTimeMillis());
		FileOutputStream fw = new FileOutputStream(MAIN_FILE);
		BufferedOutputStream bout = new BufferedOutputStream(fw);
		DataOutputStream dout = new DataOutputStream(bout);

		 for (int i = 0; i < ITEM_COUNT; i++) {
		 int ger = random.nextInt();
		
		 ger = ger < 0 ? -ger : ger;
		 System.out.print(" " + ger);
		 dout.writeInt(ger);
		
		 }

//		for (int i = ITEM_COUNT; i > 0; i--) {
//			int ger = random.nextInt();
//
//			ger = ger < 0 ? -ger : ger;
//			System.out.print(" " + i);
//			dout.writeInt(i);
//
//		}

		dout.close();
		bout.close();
		fw.close();
		MergeSort sort = new AdvancedMergeSort();

		long start = System.currentTimeMillis();
		sort.mSort(MAIN_FILE);

		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000 + "s");
		recordFile((end - start) / 1000, true);

		DataInputStream input = new DataInputStream(new BufferedInputStream(
				new FileInputStream(MAIN_FILE), BUFFER_SIZE));
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
