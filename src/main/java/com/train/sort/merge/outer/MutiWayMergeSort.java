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

public class MutiWayMergeSort extends BaseMergeSort {

	

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

		while (count > 1) {

			index = getMinIndex(ext);
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
		}
		int sIndex = getSIndex(ext);
		dout.writeInt(ext[sIndex]);
		while (true) {
			try {
				dout.writeInt(dinlist.get(sIndex).readInt());
			} catch (Exception e) {
				dinlist.get(sIndex).close();
				break;
			}
		}
		dout.close();
		return RESULT_FILE;
	}

	public static void main(String[] args) throws IOException {
		MutiWayMergeSort sort = new MutiWayMergeSort();
		sort.createBigMainFile(MAIN_FILE);

		

		long start = System.currentTimeMillis();
		RESULT_FILE = sort.sort(MAIN_FILE);

		long end = System.currentTimeMillis();
		
		recordFile((end - start) / 1000, true);
		System.out.println("cost:"+(end - start)  + "ms");
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
