package de.hartlit.opensaft.common.geodetic;




/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 */
public class CoordinatesUtils {

	// MI / 180
	private static double d2r = 0.0174532925199433d;

	/**
	 * semicircles = degrees * ( 2^31 / 180 )
	 * 
	 * @param i
	 * @return
	 */
	public static double semicircles2degrees(Integer i) {
		return i.longValue() * 180L / (double) 0x80000000l;
	}

	// calculate haversine distance for linear distance
	public static double haversine_km(double lat1, double long1, double lat2, double long2) {
		double dlong = (long2 - long1) * d2r;
		double dlat = (lat2 - lat1) * d2r;
		double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(lat1 * d2r) * Math.cos(lat2 * d2r)
				* Math.pow(Math.sin(dlong / 2.0), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return 6370.91457000 * c;
	}
	
	public static double vincenty_km(double lat1, double long1, double lat2, double long2) {
		return VincentyDistanceCalculator.getDistance(lat1, long1, lat2, long2);
	}	

}
