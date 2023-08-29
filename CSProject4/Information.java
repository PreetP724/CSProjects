package pnpatel.hw4;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 * Records information about a graph.
 * 
 * Each vertex in the graph is identified by unique integer, id, that is in the range from 0
 * up to and including graph.V();
 * 
 * It is convenient to associate a GPS location with each vertex, and we use a 
 * SeparateChainingHashST<Integer,GPS> to do so.
 * 
 * It is convenient to associate a String label with each vertex, and we use a
 * SeparateChainingHashST<Integer,String> to do so.
 * 
 * DO NOT MODIFY OR COPY. USE AS IS.
 */
public class Information {
	public final Graph graph;
	
	/** GPS locations associated with each vertex. */
	public final SeparateChainingHashST<Integer, GPS> positions;
	
	/** Labels for each vertex. */
	public final SeparateChainingHashST<Integer, String> labels;
	
	public Information (Graph g, SeparateChainingHashST<Integer, GPS> pos, SeparateChainingHashST<Integer, String> labels) {
		this.graph = g;
		this.positions = pos;
		this.labels = labels;
	}
}
