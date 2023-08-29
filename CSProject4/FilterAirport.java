package pnpatel.hw4;

/**
 * DO NOT MODIFY OR COPY. Leave this interface alone in algs.hw4.map
 */
public interface FilterAirport {

	/**
	 * Given a country (such as "United States" or "Mexico") and a GPS location, return true
	 * if that GPS location is acceptable.
	 */
	boolean accept(String country, GPS gps);
}
