package pnpatel.hw1;

import algs.hw1.fixed.*;
import algs.hw1.fixed.er.Location;
import algs.hw1.fixed.er.SearchForRectangle;
import algs.hw1.fixed.er.TrialEmbeddedRectangle;

/**
 * COPY this class into your USERID.hw1 package and complete the implementation of search.
 * 
 * This method must return a {@link Location} object which records the (startr,startc,r,c) of the rectangle that you 
 * found within the EmbeddedRectangle.
 * 
 * For example, given an EmbeddedRectangle with 5 rows and 9 columns
 * might appear as below
 * 
 *    000000000
 *    000111110
 *    000111110
 *    000111110
 *  
 * Your challenge will be to find the location of randomly generated rectangles within a Storage of suitable 
 * dimension. For the above Storage, your program should report (row=1, col=3, numRows=4, numCols=5).
 */
public class ComputeRectangle implements SearchForRectangle {
	


	@Override
	public Location search(TwoDimensionalStorage rect) {
		
		int startr = 0;
		int startc = 0;
		int r = 0;
		int c = 0;
		
		
		
		
		
		int lo = 0;
		int hi = rect.numRows/2;
		int mid = (lo + hi)/2;
		while(lo <= hi) {
			mid = (lo + hi) /2;
			if(rect.getValue(mid, rect.numColumns/2) >= 1) {
				hi = mid -1;
			}
			else {
				lo = mid + 1;
			}
		}
		startr = lo;
		
			lo = 0;
			hi = rect.numColumns/2;
			while(lo <= hi) {
				mid = (lo + hi) /2;
				if(rect.getValue(startr, mid) >= 1) {
					hi = mid -1;
				}
				else {
					lo = mid + 1;
				}
			}
			
			startc = lo;
			
		
		//System.out.println(startr);
		//System.out.println(startc);
		lo = startc+rect.numColumns/2 - 1;
		hi = rect.numColumns - 1;
		while(lo <= hi) {
			mid = (lo + hi)/2;
			if(rect.getValue(startr, mid) >= 1) {
				lo = mid + 1;
			}
			else {
				hi = mid -1;
			}
		}
		c = hi - startc + 1;
		//System.out.println(c);
		lo = startr+rect.numRows/2 - 1;
		hi = rect.numRows - 1;
		while(lo <= hi) {
			mid = (lo + hi)/2;
			if(rect.getValue(mid, startc) >= 1) {
				lo = mid + 1;
			}
			else {
				hi = mid -1;
			}
		}
		
		r = hi - startr + 1;
		//System.out.println(r);
	
		Location rectangle = new Location(startr, startc, r, c);
		return rectangle;
	}
	
	
	
	
	

	/** YOU DO NOT HAVE TO MODIFY THIS MAIN METHOD. RUN AS IS. */
	public static void main(String[] args) {
		// This code helps you evaluate if you have it working for a small example.
		int[][] values = new int[][] { 
			{ 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1 },
			{ 0, 0, 1, 1, 1, 1, 1 },
			{ 0, 0, 1, 1, 1, 1, 1 },
			{ 0, 0, 1, 1, 1, 1, 1 },
		};
		TwoDimensionalStorage sample = new TwoDimensionalStorage(values);
		SearchForRectangle me = new ComputeRectangle();
		Location loc = me.search(sample);
		System.out.println("Location 1 2 4 5 should be: " + loc);
		
		// This code is used to ensure your code is robust enough to handle a small run.

		// compute and validate it works for small run.
		TrialEmbeddedRectangle.runSampleTrial(new ComputeRectangle());

		// compute and validate for large run. Your algorithm must significantly outperform this result.
		// When I ran my naive solution, the result was 1082400000. You need to do better!
		TrialEmbeddedRectangle.leaderBoard(new ComputeRectangle());
	}
}

