package pnpatel.hw4;


import algs.hw4.map.FilterAirport;
import algs.hw4.map.FlightMap;
import algs.hw4.map.Information;
import edu.princeton.cs.algs4.DepthFirstPaths;

public class Connected {
	
public static void main(String[] args) throws Exception {
		
		// Note: You will complete this filter implementation
		FilterAirport justLower48 = new FilterLower48();
		
		Information southwest = FlightMap.undirectedGraphFromResources("southwest.json", justLower48);  
		Information delta = FlightMap.undirectedGraphFromResources("delta.json", justLower48); 
		String airline = null;
		String airports[] = new String[2];
		int index = 0;
		Iterable<Integer> skeys = southwest.labels.keys();
		for(int skey : skeys) {
			if(southwest.labels.get(skey).equals("KBOS")) {
				DepthFirstPaths sgraph = new DepthFirstPaths(southwest.graph, skey);
				for(int i = 0; i < southwest.graph.V(); i++) {
					if(!sgraph.hasPathTo(i)) {
						airline = "Southwest";
						airports[index] = southwest.labels.get(i);
						index++;
					}
				}
			}
		}
		
		if(airports[0] == null) {
		index = 0;
		Iterable<Integer> dkeys = delta.labels.keys();
		for(int dkey : dkeys) {
			if(delta.labels.get(dkey).equals("KBOS")) {
				DepthFirstPaths dgraph = new DepthFirstPaths(delta.graph, dkey);
				for(int i = 0; i < delta.graph.V(); i++) {
					if(!dgraph.hasPathTo(i)) {
						airline = "Delta";
						airports[index] = delta.labels.get(i);
						index++;
					}
				}
			}
		}
		}
		
		System.out.println("The name of the airline is " + airline);
		System.out.println("The airports that cannot be reached from KBOS using " + airline + " are:");
		System.out.println(airports[0]);
		System.out.println(airports[1]);
				
		
		
	
}
}
