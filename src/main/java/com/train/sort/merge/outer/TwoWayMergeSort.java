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
import java.util.Iterator;
import java.util.Random;

public class TwoWayMergeSort extends BaseMergeSort {

	

	// recursive method to merge the lists until we are left with a
	// single merged list
	public File merge(ArrayList<File> list) throws IOException {
		if (list.size() == 1) {
			return list.get(0);
		}
		ArrayList<File> inter = new ArrayList<File>();
		for (Iterator<File> itr = list.iterator(); itr.hasNext();) {
			File one = itr.next();
			if (itr.hasNext()) {
				File two = itr.next();
				inter.add(merge(one, two));
			} else {
				// return one;
				inter.add(one);
			}
		}
		return merge(inter);
	}

	/**
	 * 二路归并
	 * 
	 * @param one
	 * @param two
	 * @return
	 * @throws IOException
	 */
	private File merge(File one, File two) throws IOException {
		FileInputStream fis1 = new FileInputStream(one);
		FileInputStream fis2 = new FileInputStream(two);
		BufferedInputStream bin1 = new BufferedInputStream(fis1, BUFFER_SIZE);
		BufferedInputStream bin2 = new BufferedInputStream(fis2, BUFFER_SIZE);

		DataInputStream din1 = new DataInputStream(bin1);
		DataInputStream din2 = new DataInputStream(bin2);

		File output = new File("merged" + new Random().nextInt());
		FileOutputStream os = new FileOutputStream(output);
		BufferedOutputStream bout = new BufferedOutputStream(os);
		DataOutputStream dout = new DataOutputStream(bout);

		int a = -1;// = din1.readInt();
		int b = -1;// = din2.readInt();

		boolean finished = false;
		boolean emptyA = false;//
		int flag = 0;
		while (!finished) {

			if (flag != 1) {
				try {
					a = din1.readInt();
				} catch (Exception e) {
					emptyA = true;
					break;
				}
			}
			if (flag != 2) {
				try {
					b = din2.readInt();
				} catch (Exception e) {
					emptyA = false;
					break;
				}
			}
			if (a > b) {
				dout.writeInt(b);
				flag = 1;
			} else if (a < b) {
				dout.writeInt(a);
				flag = 2;
			} else if (a == b) {
				dout.write(a);
				dout.write(b);
				flag = 0;
			}
		}
		finished = false;
		if (emptyA) {
			dout.writeInt(b);
			while (!finished) {
				try {
					b = din2.readInt();
				} catch (Exception e) {
					break;
				}
				dout.writeInt(b);
			}
		} else {
			dout.writeInt(a);
			while (!finished) {
				try {
					a = din1.readInt();
				} catch (Exception e) {
					break;
				}
				dout.writeInt(a);
			}
		}
		dout.close();
		os.close();
		bin1.close();
		bin2.close();
		bout.close();
		return output;
	}

	public static void main(String[] args) throws IOException {
		TwoWayMergeSort sort = new TwoWayMergeSort();
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
