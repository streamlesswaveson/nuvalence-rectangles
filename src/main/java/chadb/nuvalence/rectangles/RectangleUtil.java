package chadb.nuvalence.rectangles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RectangleUtil {
    public static Set<Point> intersection(Rectangle r1, Rectangle r2) {
        // assume unique rectangles
        if (r1.equals(r2)) throw new IllegalArgumentException("Rectangles cannot be equal");

        Set<Point> result = new HashSet<>();

        addIntersection(r1, r2, result);
        addIntersection(r2, r1, result);

        return result;
    }

    private static void addIntersection(Rectangle r1, Rectangle r2, Set<Point> result) {
        List<Line> horiz = r1.getHorizontal();
        List<Line> vert = r2.getVertical();

        for (Line h : horiz) {
            for (Line v : vert) {
                Point point = RectangleUtil.intersect(h, v);
                if (null != point) result.add(point);
            }
        }
    }

    public static boolean contains(Rectangle r, Point p) {
        return r.getLowerLeft().getX() <= p.getX() && p.getX() <= r.getUpperRight().getX() &&
                r.getLowerLeft().getY() <= p.getY() && p.getY() <= r.getUpperRight().getY();
    }

    /**
     * @param horizontal
     * @param vertical
     * @return
     */
    public static Point intersect(final Line horizontal, final Line vertical) {
        // Logic assumes line one is horizontal and two vertical
        // swap if horizontal and vertical lines switched
        Line one = horizontal;
        Line two = vertical;
        if (one.getA().getX() == one.getB().getX()) {
            Line temp = one;
            one = two;
            two = temp;
        }
        // if oneMinX < twoX < oneMaxX AND twoMinY < oneY < twoMaxY => point(twoX,oneY)
        if (one.getA().getX() < two.getA().getX() && two.getA().getX() < one.getB().getX() &&
                two.getA().getY() < one.getA().getY() && one.getA().getY() < two.getB().getY()) {

            return new Point(two.getA().getX(), one.getA().getY());
        }
        return null;
    }
}
