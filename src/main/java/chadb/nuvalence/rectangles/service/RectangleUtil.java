package chadb.nuvalence.rectangles.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides logic to determine whether rectangles intersect, are adjacent, and/or contain one another.
 */
public class RectangleUtil {

    /**
     * Returns intersection points and throws IllegalArgumentException if rectangles are equal
     *
     * @param r1
     * @param r2
     * @return Set of intersection points
     */
    public static Set<Point> intersection(Rectangle r1, Rectangle r2) {
        // assume unique rectangles
        if (r1.equals(r2)) throw new IllegalArgumentException("Rectangles cannot be equal");

        Set<Point> result = new HashSet<>();

        addIntersection(r1, r2, result);
        addIntersection(r2, r1, result);

        return result;
    }

    /**
     * Adds intersection point, if any, between two rectangles
     *
     * @param r1
     * @param r2
     * @param result
     */
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

    /**
     * Finds point of intersection between two orthogonal lines
     *
     * @param horizontal
     * @param vertical
     * @return
     */
    protected static Point intersect(final Line horizontal, final Line vertical) {
        // assumes line one is horizontal and line two is vertical
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

    /**
     * @param r
     * @param p
     * @return whether Rectangle contains a point
     */
    public static boolean contains(Rectangle r, Point p) {
        return r.getLowerLeft().getX() <= p.getX() && p.getX() <= r.getUpperRight().getX() &&
                r.getLowerLeft().getY() <= p.getY() && p.getY() <= r.getUpperRight().getY();
    }

    /**
     * @param rect1
     * @param rect2
     * @return whether two rectangles are adjacent
     */
    public static boolean isAdjacent(Rectangle rect1, Rectangle rect2) {
        for (Line line1 : rect1.getHorizontal()) {
            for (Line line2 : rect2.getHorizontal()) {
                if (adjacent(line1, line2)) return true;
            }
        }

        for (Line line1 : rect1.getVertical()) {
            for (Line line2 : rect2.getVertical()) {
                if (adjacent(line1, line2)) return true;
            }
        }
        return false;
    }

    /**
     * @param line1
     * @param line2
     * @return whether two lines are adjacent
     */
    protected static boolean adjacent(final Line line1, final Line line2) {
        // if y axis same
        int yaxis = line1.getA().getY();
        if (yaxis == line1.getB().getY() && yaxis == line2.getA().getY() && yaxis == line2.getB().getY()) {
            return betweenX(line1, line2) || betweenX(line2, line1);
        }

        int xaxis = line1.getA().getX();
        if (xaxis == line1.getB().getX() && xaxis == line2.getA().getX() && xaxis == line2.getB().getX()) {
            return betweenY(line1, line2) || betweenY(line2, line1);
        }

        return false;
    }

    /**
     * @param line1
     * @param line2
     * @return whether two lines on the x axis overlap
     */
    private static boolean betweenX(final Line line1, final Line line2) {
        return (line1.getA().getX() <= line2.getA().getX() && line2.getA().getX() <= line1.getB().getX());
    }

    /**
     * @param line1
     * @param line2
     * @return whether two lines on the y axis overlap
     */
    private static boolean betweenY(final Line line1, final Line line2) {
        return (line1.getA().getY() <= line2.getA().getY() && line2.getA().getY() <= line1.getB().getY());
    }


}
