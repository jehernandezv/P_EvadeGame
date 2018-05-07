package test;

import utility.Utility;

public class Test {
	public static void main(String[] args) {
		double grade = 360 - Utility.atPolar(110, 54);
		System.out.println(grade);
	}

}
