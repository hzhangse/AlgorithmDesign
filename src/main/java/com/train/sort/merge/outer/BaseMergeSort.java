package com.train.sort.merge.outer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import com.train.tree.HeapTree;
import com.train.tree.LoserTree;
import com.train.tree.Result;

public abstract class BaseMergeSort {
	public static int ITEM_COUNT = 10; // 总数

	public static int BUFFER_SIZE = 1024;// 一次缓冲读取

	public static int FILE_COUNT = 4;// 每个文件的记录数1

	public static File MAIN_FILE = new File("mainset.txt");// 要排序的文件

	public static File RESULT_FILE = new File("result.txt");// 要排序的文件
	

	
	public File sort(File file) throws IOException {
		ArrayList<File> files = split(file);
		return merge(files);
	}
	

	abstract public File merge(ArrayList<File> list ) throws IOException ;
	

	
	protected File writeSplitSortedFile(int index, int[] buffer) {
		DataOutputStream dout = null;
		FileOutputStream writer = null;
		BufferedOutputStream bOutputStream = null;
		File f = new File("set" + new Random().nextInt());
		try {
			writer = new FileOutputStream(f);
			bOutputStream = new BufferedOutputStream(writer);

			dout = new DataOutputStream(bOutputStream);
			System.out.println("splt file:"+f.getName()+ " with content:");
			for (int j = 0; j < index; j++) {
				System.out.print(" " + buffer[j]);

				dout.writeInt(buffer[j]);
			}
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dout != null && bOutputStream != null && writer != null)
				try {
					dout.close();
					bOutputStream.close();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return f;
	}

	/**
	 * Splits the original file into a number of sub files.
	 */
	protected ArrayList<File> split(File file) throws IOException {
		ArrayList<File> files = new ArrayList<File>();
		int[] buffer = new int[FILE_COUNT];
		FileInputStream fr = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(fr, BUFFER_SIZE);
		DataInputStream din = new DataInputStream(bin);
		boolean fileComplete = false;

		while (!fileComplete) {
			int index = buffer.length;
			for (int i = 0; i < buffer.length && !fileComplete; i++) {
				try {
					buffer[i] = din.readInt();
				} catch (Exception e) {
					fileComplete = true;
					index = i;
				}
			}
			if (index != 0 && buffer[0] > -1) {
				Arrays.sort(buffer, 0, index);
				File f = writeSplitSortedFile(index,buffer);
				files.add(f);

			}

		}
		din.close();
		bin.close();
		fr.close();
		return files;
	}

	

	// 找到剩下的最后一个文件输入流
	public int getSIndex(int[] ext) {
		int result = 0;
		for (int i = 0; i < ext.length; i++) {
			if (ext[i] != -1) {
				result = i;
				break;
			}
		}
		return result;
	}

	// 找到数据中最小的一个
	public int getMinIndex(int[] ext) {
		int min = 2147483647;
		int index = -1;
		for (int i = 0; i < ext.length; i++) {
			if (ext[i] != -1 && ext[i] < min) {
				min = ext[i];
				index = i;
			}
		}
		return index;
	}

	
	public  void createBigMainFile(File file)throws IOException{
		Random random = new Random(System.currentTimeMillis());
		FileOutputStream fw = new FileOutputStream(file);
		BufferedOutputStream bout = new BufferedOutputStream(fw);
		DataOutputStream dout = new DataOutputStream(bout);
		System.out.println(" Create Big Mail Content:");
		for (int i = 0; i < ITEM_COUNT; i++) {
			int ger = random.nextInt();

			ger = ger < 0 ? -ger : ger;
			System.out.print(" " + ger);
			dout.writeInt(ger);

		}
		System.out.println();
		dout.close();
		bout.close();
		fw.close();
	}
	
	
	protected static void recordFile(long time, boolean isBuffer)
			throws FileNotFoundException, IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("log", true));
		bw.write("FILE_COUNT = " + FILE_COUNT + ";对" + ITEM_COUNT + "条数据 "
				+ ITEM_COUNT * 4 / (1024 * 1204) + "MB排序耗时:" + time + "s ");
		if (isBuffer) {
			bw.write("  使用缓冲:" + BUFFER_SIZE * 4 / (1024 * 1204) + "MB");
		}
		bw.newLine();
		bw.close();
	}
}
