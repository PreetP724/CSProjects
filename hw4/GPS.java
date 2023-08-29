package pnpatel.hw4;

/**
 * Each GPS coordinate is defined by a (latitude, longitude) pair.
 * 
 * DO NOT MODIFY OR COPY. USE AS IS.
 */
public class GPS {
	
	public final float latitude;
	public final float longitude;
	
	public GPS (float lat, float lngt) {
		this.latitude = lat;
		this.longitude = lngt;
	}
	
	/** Helper constructor, in case double values are available instead. */
	public GPS (double lat, double lngt) {
		this.latitude = (float) lat;
		this.longitude = (float) lngt;
	}

	/**
	 * Return reasonably distance in miles. Based on helpful method found here:
     *
	 * https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
	 */
	public double distance(GPS other) {
		double lat1 = latitude;
		double lat2 = other.latitude;
		double long1 = longitude;
		double long2 = other.longitude;
	    double p = Math.PI/180;
	    double a = 0.5 - Math.cos((lat2-lat1)*p)/2 + Math.cos(lat1*p) * Math.cos(lat2*p) * (1-Math.cos((long2-long1)*p))/2;
	    return 7917.509282 * Math.asin(Math.sqrt(a));    // convert into miles and use 12742 as earth diameter in KM
	}
	
	public String toString() { return "<" + latitude + "," + longitude + ">"; }
}
