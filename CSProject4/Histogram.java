package pnpatel.hw4;

import java.util.Random;

import algs.hw3.ID;
import edu.princeton.cs.algs4.AVLTreeST;

/** 
 * COPY this class into your USERID.hw4 package and complete this class.
 */
public class Histogram {

	// You will need some symbol table that you can use to store the keys 
	// in such a way that you can retrieve them in order.
	AVLTreeST<Integer, Integer> data = new AVLTreeST<>();
	/** 
	 * Increase the count for the number of times 'key' exists in the Histogram.
	 * @param n
	 */
	public void record(int key) {
		if(!data.contains(key)) {
			data.put(key, 1);
			return;
		}
		int value = data.get(key);
		data.put(key, value + 1);
	}
	
	/** Return whether histogram is empty. */
	public boolean isEmpty() {
		return data.isEmpty();
		//throw new RuntimeException ("COMPLETE ME");
	}
	
	/** Return the lowest integer key in the histogram. */
	public int minimum() { 
		return data.min();		
		//throw new RuntimeException ("COMPLETE ME");
	}

	/** Return the largest integer key in the histogram. */
	public int maximum() { 
		return data.max();
		//throw new RuntimeException ("COMPLETE ME");
	}
	
	/** Return sum of counts for keys from lo (inclusive) to high (inclusive). */
	public int total(int lo, int hi) {
		Iterable<Integer> keys = data.keysInOrder();
		int sum = 0;
		for(int key: keys) {
			if(key >= lo && key <= hi) {
				sum += data.get(key);
			}
		}
		return sum;
		//throw new RuntimeException ("COMPLETE ME");
	}
	
	/** Produce a report for all keys (and their counts) in ascending order of keys. */
	public void report() {
		Iterable<Integer> keys = data.keysInOrder();
		System.out.println("Raw Histogram");
		for (int key : keys) {
			System.out.println(key + " " + data.get(key));
		}
		//throw new RuntimeException ("COMPLETE ME");
	}
	
	/** 
	 * Produce a report for all bins (with aggregate counts) in ascending order by range.
	 * 
	 * The first range label that is output should be "0 - (binSize-1)" since the report always starts from 0.
	 * 
	 * It is acceptable if the final range label includes values that exceed maximum().
	 */
	public void report(int binSize) {
		System.out.println("Histogram (binSize=" + binSize + ")");
		for (int i = 0; i <= maximum(); i+= binSize) {
			System.out.println(String.format("%d-%d\t%d", i, i+binSize-1, total(i, i+binSize-1)));
		}
	}
	
	/** 
	 * Kick things off with random integers, but using a fixed generated sequence so you can reproduce.
	 * 
	 * USE THIS METHOD AS IS WITHOUT ANY CHANGES.
	 */
	public static void main(String[] args) {
		Histogram sample = new Histogram();
		
		Random rnd = new Random(0);
		for (int i = 0; i < 20; i++) {
			int v = rnd.nextInt(20);
			sample.record(v);
		}
		
		sample.report();
		sample.report(1);
		sample.report(5);
	}
}
