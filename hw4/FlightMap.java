package pnpatel.hw4;

import java.io.*;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 * Demonstrate how to load up a TMG highway segment file into a graph. Currently
 * the only graph is an undirected graph with no edge weights.
 * 
 * DO NOT MODIFY OR COPY. USE AS IS.
 */
public class FlightMap {
	/**
	 * Load up Information about one of the airlines (either "delta.json" or "southwest.json")
	 * and filter out airlines that do not match the filter criteria that is passed in as an argument.
	 */
	public static Information undirectedGraphFromResources(String resource, FilterAirport filter) {
		try {
			URL rsrc = FlightMap.class.getResource("/algs/hw4/resources/" + resource);
			return undirectedGraph(rsrc.openStream(), filter);
		} catch (IOException ioe) {
			System.err.println("Failed to read graph:" + ioe.getMessage());
			return null;
		}
	}

	/**
	 * Parse the necessary JSON file, filtering out airports that do not pass the 'filter' check,
	 * and return an Information object, with all acceptable airports and their GPS locations with
	 * labels. 
	 */
	public static Information undirectedGraph(InputStream is, FilterAirport filter) {
		try {
			JSONArray topLevel = (JSONArray) new JSONParser().parse(new InputStreamReader(is));
			
			int airport = 0;
			SeparateChainingHashST<String, Integer> airports = new SeparateChainingHashST<>();
			
			SeparateChainingHashST<Integer, GPS> airport_gps = new SeparateChainingHashST<>();
			SeparateChainingHashST<Integer, String> airport_labels = new SeparateChainingHashST<>();
			
			Queue<Edge> edges = new Queue<>();
			
			for (int i = 0; i < topLevel.size(); i++) {
				JSONObject flight = (JSONObject) topLevel.get(i);

				JSONObject a1 = (JSONObject) flight.get("airport1");
				String country1 = (String) a1.get("country");
				
				JSONObject a2 = (JSONObject) flight.get("airport2");
				String country2 = (String) a2.get("country");
				
				String sign1 = (String) a1.get("icao");  // get airport code. This is UNIQUE
				double longitude1 = (Double) a1.get("lon");
				double latitude1 = (Double) a1.get("lat");
				GPS gps1 = new GPS(latitude1, longitude1);
				
				String sign2 = (String) a2.get("icao");  // get airport code. This is UNIQUE
				double longitude2 = (Double) a2.get("lon");
				double latitude2 = (Double) a2.get("lat");
				GPS gps2 = new GPS(latitude2, longitude2);
				
				// If a filter exists, only continue if both are acceptable.
				if (filter != null && !filter.accept(country1, gps1)) { continue; }
				if (filter != null && !filter.accept(country2, gps2)) { continue; }
				
				if (!airports.contains(sign1)) {
					airport_gps.put(airport, gps1);
					airport_labels.put(airport, sign1);
					airports.put(sign1, airport++); 
				}
				
				if (!airports.contains(sign2)) {
					airport_gps.put(airport, gps2);
					airport_labels.put(airport, sign2);
					airports.put(sign2, airport++); 
				}
				
				// keep track of edges for later
				edges.enqueue(new Edge(airports.get(sign1), airports.get(sign2)));
			}
			
			// construct the graph. Make sure that the same edge is not added TWICE.
			Graph graph = new Graph(airport);
			for (Edge e : edges) {
				boolean alreadyThere = false;
				for (int w : graph.adj(e.u)) {
					if (w == e.v) { alreadyThere = true; break; } 
				}
				if (!alreadyThere) {
					graph.addEdge(e.u,  e.v);
				}
			}
			
			return new Information (graph, airport_gps, airport_labels);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// not sure what to do!
		return null;
	}
}
