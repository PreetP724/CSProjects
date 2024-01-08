package pnpatel.hw4;

import algs.hw4.map.FilterAirport;
import algs.hw4.map.FlightMap;
import algs.hw4.map.Information;
import edu.princeton.cs.algs4.AdjMatrixEdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.FloydWarshall;

public class LongestofShortest {

	public static void main(String[] args) throws Exception {
		
		// Note: You will complete this filter implementation
		FilterAirport justLower48 = new FilterLower48();
		
		Information southwest = FlightMap.undirectedGraphFromResources("southwest.json", justLower48);  
		Information delta = FlightMap.undirectedGraphFromResources("delta.json", justLower48); 
		
		
		
		AdjMatrixEdgeWeightedDigraph sgraph = new AdjMatrixEdgeWeightedDigraph(southwest.graph.V());
		for(int i = 0; i < southwest.graph.V(); i++) {
			for(int adjacents: southwest.graph.adj(i)) {
				DirectedEdge one = new DirectedEdge(i, adjacents, southwest.positions.get(i).distance(southwest.positions.get(adjacents)));
				sgraph.addEdge(one);
			}
		}
		
		AdjMatrixEdgeWeightedDigraph dgraph = new AdjMatrixEdgeWeightedDigraph(delta.graph.V());
		for(int i = 0; i < delta.graph.V(); i++) {
			for(int adjacents: delta.graph.adj(i)) {
				DirectedEdge one = new DirectedEdge(i, adjacents, delta.positions.get(i).distance(delta.positions.get(adjacents)));
				dgraph.addEdge(one);
			}
		}
		
		FloydWarshall ssearch = new FloydWarshall(sgraph);
		FloydWarshall dsearch = new FloydWarshall(dgraph);
		
		double slongest = Double.MIN_VALUE;
		int sfrom = -1;
		int sto = -1;
		double sefficiency[][] = new double[southwest.graph.V()][southwest.graph.V()];
		double sairportdistance = 0;
		for(int i = 0; i < southwest.graph.V(); i++) {
			for(int k = 0; k < southwest.graph.V(); k++) {
				sefficiency[i][k] = 0;
				if(i != k) {
				double sshortest = ssearch.dist(i, k);
				sefficiency[i][k] = sshortest/southwest.positions.get(i).distance(southwest.positions.get(k));
				}
				if(ssearch.dist(i, k) != Double.POSITIVE_INFINITY && ssearch.dist(i,k) > slongest) {
					slongest = ssearch.dist(i, k);
					sfrom = i;
					sto = k;
					sairportdistance = southwest.positions.get(i).distance(southwest.positions.get(k));
				}
			}
				
		}
		
		double dlongest = Double.MIN_VALUE;
		int dfrom = -1;
		int dto = -1;
		double dairportdistance = 0;
		int countinf = 0;
		double defficiency[][] = new double[delta.graph.V()][delta.graph.V()];
		for(int i = 0; i <delta.graph.V(); i++) {
			for(int k = 0; k < delta.graph.V(); k++) {
				defficiency[i][k] = 0;
				if(i != k) {
				double dshortest = dsearch.dist(i, k);
				if(dshortest == Double.POSITIVE_INFINITY) {
					countinf++;
				}
				if(dshortest != Double.POSITIVE_INFINITY) {
				defficiency[i][k] = dshortest/delta.positions.get(i).distance(delta.positions.get(k));
				}
				}
				if(dsearch.dist(i,k) != Double.POSITIVE_INFINITY && dsearch.dist(i, k) > dlongest) {
					dlongest = dsearch.dist(i, k);
					dfrom = i;
					dto = k;
					dairportdistance = delta.positions.get(i).distance(delta.positions.get(k));
				}
			}
				
		}

		
		
		System.out.println("DELTA");
		System.out.println("Total Flight Distance is " + dlongest + " but airports are only " + dairportdistance + " miles apart.");
		Iterable<DirectedEdge> dpath = dsearch.path(dfrom, dto);
		for(DirectedEdge edge : dpath) {
			int head = edge.from();
			int tail = edge.to();
			System.out.println(delta.labels.get(head) + " -> " + delta.labels.get(tail) + " for " + edge.weight() + " miles.");
		}
		double dtotal = 0;
		for(int i = 0; i < defficiency.length; i++) {
			for(int k = 0; k < defficiency.length; k++) {
				if(i != k) {
					dtotal += defficiency[i][k];
				}
			}
		}
		
		
		int dnum = defficiency.length * defficiency.length - defficiency.length - countinf;
		//System.out.println(dnum);
		System.out.println("Average Efficiency: " + dtotal/dnum);
		
		System.out.println("");
		
		System.out.println("SOUTHWEST");
		System.out.println("Total Flight Distance is " + slongest + " but airports are only " + sairportdistance + " miles apart.");
		Iterable<DirectedEdge> spath = ssearch.path(sfrom, sto);
		for(DirectedEdge edge : spath) {
			int head = edge.from();
			int tail = edge.to();
			System.out.println(southwest.labels.get(head) + " -> " + southwest.labels.get(tail) + " for " + edge.weight() + " miles.");
		}
		
		double stotal = 0;
		for(int i = 0; i < sefficiency.length; i++) {
			for(int k = 0; k < sefficiency.length; k++) {
				if(i != k) {
					stotal += sefficiency[i][k];
				}
			}
		}
		int snum = sefficiency.length * sefficiency.length - sefficiency.length;
		System.out.println("Average Efficiency: " + stotal/snum);
	
		
		
		
}
}
