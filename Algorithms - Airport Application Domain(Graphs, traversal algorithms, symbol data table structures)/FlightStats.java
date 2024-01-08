package pnpatel.hw4;

import algs.hw4.map.FilterAirport;
import algs.hw4.map.FlightMap;
import algs.hw4.map.Information;
import algs.hw4.map.GPS;

public class FlightStats {
	

public static void main(String[] args) throws Exception {
		
		// Note: You will complete this filter implementation
		FilterAirport justLower48 = new FilterLower48();
		
		Information southwest = FlightMap.undirectedGraphFromResources("southwest.json", justLower48);  
		Information delta = FlightMap.undirectedGraphFromResources("delta.json", justLower48); 
		
		int stotal = 0;
		double sflights = southwest.graph.E();
		int slongest = 0;
		int sshortest = Integer.MAX_VALUE;
		double saverage =0;
		String slongfrom = null;
		String slongto = null;
		String sshortfrom = null;
		String sshortto = null;
		Histogram sdata = new Histogram();
		
		int dtotal = 0;
		double dflights = delta.graph.E();
		int dlongest = 0;
		int dshortest = Integer.MAX_VALUE;
		double daverage =0;
		String dlongfrom = null;
		String dlongto = null;
		String dshortfrom = null;
		String dshortto = null;
		Histogram ddata = new Histogram();
		
		for(int i = 0; i < southwest.graph.V(); i ++) {
			GPS location1 = southwest.positions.get(i);
			
			Iterable<Integer> adjacent = southwest.graph.adj(i);
			for(int flights : adjacent) {
				if(i > flights) {
				GPS location2 = southwest.positions.get(flights);
				int distance = (int) location1.distance(location2);
				sdata.record(distance);
				stotal += distance;
				if( distance > slongest) {
					slongest = distance;
					slongfrom = southwest.labels.get(i);
					slongto = southwest.labels.get(flights);
				}
				else if(distance < sshortest) {
					sshortest = distance;
					sshortfrom = southwest.labels.get(i);
					sshortto = southwest.labels.get(flights);
				}
				
			}
		}
		}
		
		saverage = ((stotal))/sflights;
		
		
		for(int i = 0; i < delta.graph.V(); i ++) {
			GPS dlocation1 = delta.positions.get(i);
			Iterable<Integer> adjacent = delta.graph.adj(i);
			for(int flights : adjacent) {
				if(i > flights) {
				GPS dlocation2 = delta.positions.get(flights);
				int distance = (int) dlocation1.distance(dlocation2);
				ddata.record(distance);
				dtotal += distance;
				if( distance > dlongest) {
					dlongest = distance;
					dlongfrom = delta.labels.get(i);
					dlongto = delta.labels.get(flights);
				}
				else if(distance < dshortest) {
					dshortest = distance;
					dshortfrom = delta.labels.get(i);
					dshortto = delta.labels.get(flights);
				}
				
			}
			}
		}
		
		daverage = ((dtotal))/dflights;
		
		System.out.println("Shortest flight for Delta is from " + dshortfrom + " to " + dshortto + " for " + dshortest + " miles.");
		System.out.println("Longest flight for Delta is from " + dlongfrom + " to " + dlongto + " for " + dlongest + " miles.");
		System.out.println("Average Delta flight distance = " + daverage);
		System.out.println("");
		System.out.println("Shortest flight for Southwest is from " + sshortfrom + " to " + sshortto + " for " + sshortest + " miles.");
		System.out.println("Longest flight for Southwest is from " + slongfrom + " to " + slongto + " for " + slongest + " miles.");
		System.out.println("Average Southwest flight distance = " + saverage);
		System.out.println("");
		System.out.println("Delta Airlines:");
		ddata.report(500);
		System.out.println("");
		System.out.println("Southwest Airlines:");
		sdata.report(500);
}
}
