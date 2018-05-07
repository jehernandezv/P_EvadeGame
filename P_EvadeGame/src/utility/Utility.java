package utility;

public class Utility {

	public static boolean isInCircle(int xCenter, int yCenter, int radius, int x, int y) {
		return (Math.sqrt(Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2)) < radius);
	}

	public static boolean isInElipse(int h, int k, int xRadius, int yRadius, int x, int y) {
		return Math.pow((x - h) / xRadius, 2) + Math.pow((y - k) / yRadius, 2) <= 1;
	}
	
	public static double atPolar(double x, double y){
		double grade = 0;
		if(x > 0 && y > 0){
			y = y * -1;
			grade = Math.atan(y/ x);
		}else
		if(x > 0 && y < 0){
			y = y * -1;
			grade = Math.atan(y/ x);
		}
		
//		if(x < 0 && y > 0){
//			y = y * -1;
//			grade = Math.atan(y/ x);
//		}
//		
//		if(x < 0 && y < 0){
//			y = y *-1;
//			grade = Math.atan(y/ x);
//		}
		return Math.toDegrees(grade);
	}
}
