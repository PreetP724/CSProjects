package pnpatel.hw4;

import algs.hw4.map.FilterAirport;
import algs.hw4.map.FlightMap;
import algs.hw4.map.Information;

public class Overlap {
	

public static void main(String[] args) throws Exception {
		
		// Note: You will complete this filter implementation
		FilterAirport justLower48 = new FilterLower48();
		
		Information southwest = FlightMap.undirectedGraphFromResources("southwest.json", justLower48);  
		Information delta = FlightMap.undirectedGraphFromResources("delta.json", justLower48);  
		
		Iterable<Integer> skeys = southwest.labels.keys();
		
		
		for(int skey : skeys) {
			boolean exists = false;
			String id = southwest.labels.get(skey);
			Iterable<Integer> dkeys = delta.labels.keys();
			for(int dkey : dkeys) {
				if(delta.labels.get(dkey).equals(id)) {
					exists = true;
					break;
				}
			}
			if(!exists) {
				System.out.println(id);
			}
		}
}
}
