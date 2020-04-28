package justforcoding;

//How to check if a given point lies inside or outside a polygon?

public class FindIfPointLieInsidePolygon {
	
	static int INF = 1000000;
	public static void main(String args[]) {

		Point a[] = new Point[4];
		Point polygon[] = { new Point(0, 0), new Point(10, 0), new Point(10, 10), new Point(0, 10) };
		Point p = new Point(5, 5);

		System.out.println(isInside(polygon, p));
	}

	public static boolean isInside(Point poly[], Point p) {
		if (poly.length < 3)
			return false;
		int count = 0, i = 0;
		Point extreme = new Point(INF, p.y);
		do {
			Point p1 = poly[i];
			Point p2 = poly[(i + 1) % poly.length];

			if (doIntersect(p1, p2, p, extreme)) {
				if (orientation(p1, p2, p) == 0)
					return onSegment(p1, p2, p);
				count++;
			}
			i = (i + 1) % poly.length;
		} while (i != 0);

		return (count & 1) > 0;
	}

	public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		if (o1 != o2 && o3 != o4)
			return true;

		if (o1 == 0 && onSegment(p1, q1, p2))
			return true;
		if (o2 == 0 && onSegment(p1, q1, q2))
			return true;
		if (o3 == 0 && onSegment(p2, q2, p1))
			return true;
		if (o4 == 0 && onSegment(p2, q2, q2))
			return true;

		return false;
	}

	// Given line segment A to B, checks if C lies on it.
	public static boolean onSegment(Point a, Point b, Point c) {
		if ((c.x >= Math.min(a.x, b.x)) && (c.x <= Math.max(a.x, b.x)) && (c.y >= Math.min(a.y, b.y))
				&& (c.y <= Math.max(a.y, b.y)))
			return true;
		return false;
	}

	public static int orientation(Point a, Point b, Point c) {
		// if m1 > m2 clockwise, if m1 < m2 anticlock, if m1 == m2 collinear
		// int m1 = (b.y - a.y)/(b.x - a.x);
		// int m2 = (c.y - b.y)/(c.x - b.x);

		if ((b.y - a.y) * (c.x - b.x) > (c.y - b.y) * (b.x - a.x))
			return 1;
		else if ((b.y - a.y) * (c.x - b.x) == (c.y - b.y) * (b.x - a.x))
			return 0;
		else
			return 2;
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
