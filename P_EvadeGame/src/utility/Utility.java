package utility;

public class Utility {

	public static boolean isInCircle(int xCenter, int yCenter, int radius, int x, int y) {
		return (Math.sqrt(Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2)) < radius);
	}

	public static boolean isInElipse(int h, int k, int xRadius, int yRadius, int x, int y) {
		return Math.pow((x - h) / xRadius, 2) + Math.pow((y - k) / yRadius, 2) <= 1;
	}
	
	public static double atPolares(int x, int y){
		return Math.atan((y/x));
	}
}
