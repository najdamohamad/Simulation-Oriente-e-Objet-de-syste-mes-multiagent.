package simulateurBalles;

import java.awt.Point;

public class TestBalls {

	public static void main(String[] args) {
		Balls balles = new Balls();
		System.out.println(balles.toString());
		balles.add(new Point(1,5));
		balles.add(new Point(2,9));
		System.out.println(balles.toString());
		balles.translate(2, 3);
		System.out.println(balles.toString());
		balles.reInit();
		System.out.println(balles.toString());
	}

}
