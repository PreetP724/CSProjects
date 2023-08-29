package pnpatel.hw4;

import algs.days.day20.DepthFirstSearchNonRecursive;
import algs.hw4.map.GPS;
import algs.hw4.map.HighwayMap;
import algs.hw4.map.Information;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

/**
 * Copy this class into USERID.hw4 and make changes.
 */
public class MapSearch {
	
	/** 
	 * This helper method returns the western-most vertex id in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int westernMostVertex(Information info) {
		float lon = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < info.graph.V(); i++) {
			float val = info.positions.get(i).longitude;
			if(val < lon) {
				lon = val;
				index = i;
			}
		}
		return index;
	}

	/** 
	 * This helper method returns the western-most vertex id in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int easternMostVertex(Information info) {
		float lon = Integer.MIN_VALUE;
		int index = 0;
		for(int i = 0; i < info.graph.V(); i++) {
			float val = info.positions.get(i).longitude;
			if(val > lon) {
				lon = val;
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		Information info = HighwayMap.undirectedGraphFromResources("USA-lower48-natl-traveled.tmg");
		int west = westernMostVertex(info);
		int east = easternMostVertex(info);
		
		// DO SOME WORK HERE and have the output include things like this
		BreadthFirstPaths one = new BreadthFirstPaths(info.graph, west);
		Iterable<Integer> path = one.pathTo(east);
		
		int bedges = -1;
		double bdistance = 0;
		int bvbefore = -1;
		for(int num : path) {
			if(bvbefore  != -1) {
				bdistance += info.positions.get(bvbefore).distance(info.positions.get(num));
				
			}
			bvbefore = num;
			bedges++;
		
		}
		
		DepthFirstSearchNonRecursive two = new DepthFirstSearchNonRecursive(info.graph, west);
		Iterable<Integer> dpath = two.pathTo(east);
		
		int dedges = -1;
		double ddistance = 0;
		int dvbefore = -1;
		for(int num : dpath) {
			if(dvbefore  != -1) {
				ddistance += info.positions.get(dvbefore).distance(info.positions.get(num));
				
			}
			dedges++;
			dvbefore = num;
			
		
		}
		
		EdgeWeightedDigraph weightedgraph = new EdgeWeightedDigraph(info.graph.V());
		
		for(int i = 0; i < info.graph.V(); i++) {
			for(int adjacents : info.graph.adj(i)) {
				DirectedEdge edge = new DirectedEdge(i, adjacents, info.positions.get(i).distance(info.positions.get(adjacents)));
				weightedgraph.addEdge(edge);
			}
		}
		
		DijkstraSP process = new DijkstraSP(weightedgraph, west);
		
		double spdistance = 0;
		int spedges = 0;
		
		Iterable<DirectedEdge> weightedgs = process.pathTo(east);
		
		for(DirectedEdge edge: weightedgs) {
			spedges++;
			spdistance += edge.weight();
		}
		
		
		System.out.println("BFS: From " + info.labels.get(west) + " to " + info.labels.get(east) + " has " + bedges + " edges.");
		System.out.println("BFS provides an answer that is: " + bdistance + " miles.");
		System.out.println("DFS provides an answer that is: " + ddistance + " miles with " + dedges + " total edges.");
	//	System.out.println("Shortest Distance via Dikstra: " +  " miles.");
		System.out.println("Shortest Distance via Dijkstra: " + spdistance + " miles with " + spedges + " total edges.");
	}
}
