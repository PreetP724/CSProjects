package pnpatel.hw4;



import algs.hw4.map.FilterAirport;
import algs.hw4.map.FlightMap;
import algs.hw4.map.Information;

public class Hub {
	

public static void main(String[] args) throws Exception {
		
		// Note: You will complete this filter implementation
		FilterAirport justLower48 = new FilterLower48();
		
		Information southwest = FlightMap.undirectedGraphFromResources("southwest.json", justLower48);  
		Information delta = FlightMap.undirectedGraphFromResources("delta.json", justLower48); 
		
		String sids[] = new String[southwest.graph.V()];
		int sflights[] = new int[southwest.graph.V()];
		
		String dids[] = new String[delta.graph.V()];
		int dflights[] = new int[delta.graph.V()];
		
		int sindex = 0;
		int dindex = 0;
		
		for(int i = 0; i < southwest.graph.V(); i++) {
			int sdirects = southwest.graph.degree(i);
			if( sdirects > 75) {
				sids[sindex] = southwest.labels.get(i);
				sflights[sindex] = sdirects;
				sindex++;
			}
		}
		
		for(int i = 0; i < delta.graph.V(); i++) {
			int ddirects = delta.graph.degree(i);
			if( ddirects > 75) {
				dids[dindex] = delta.labels.get(i);
				dflights[dindex] = ddirects;
				dindex++;
			}
		}
		
		System.out.println("DELTA");
		for(int i = 0; i < dids.length; i++) {
			if(dids[i] == null) {
				break;
			}
			System.out.println(dids[i] + "       " + dflights[i]);
		}
		System.out.println("");
		System.out.println("SOUTHWEST");
		for(int i = 0; i < sids.length; i++) {
			if(sids[i] == null) {
				break;
			}
			System.out.println(sids[i] + "       " + sflights[i]);
		}
		
		
}
}
