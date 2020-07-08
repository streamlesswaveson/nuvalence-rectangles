package chadb.nuvalence.rectangles.service;

import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Immutable representation of a Rectangle
 * Rectangle is represented as a pair of two points in a cartesian plane:
 * a lower left point and an upper right point
 */
@EqualsAndHashCode
public class Rectangle {
    private final Point lowerLeft;
    private final Point upperRight;

    /**
     * Private constructor.  Use factory methods for Rectangle construction
     *
     * @param ll lower left point
     * @param ur upper right point
     */
    private Rectangle(Point ll, Point ur) {
        if (ll.getX() > ur.getX()) throw new IllegalArgumentException("Left x must be less than right x");
        if (ll.getY() > ll.getY()) throw new IllegalArgumentException("Left y must be less than right y");
        this.lowerLeft = ll;
        this.upperRight = ur;
    }

    /**
     * Factory method for cloning a Rectangle
     *
     * @param origin
     * @return Rectangle
     */
    public static Rectangle from(Rectangle origin) {
        return Rectangle.from(origin.getLowerLeft(), origin.getUpperRight());
    }

    /**
     * Creates new Rectangle from two valid points.  Factory method.
     *
     * @param ll lower left point
     * @param ur upper right point
     * @return Rectangle
     */
    public static Rectangle from(Point ll, Point ur) {
        return new Rectangle(new Point(ll), new Point(ur));
    }

    /**
     * Creates new Rectangle from two valid sets of cartesian coordinates.  Factory method
     *
     * @param lx lower left x coordinate
     * @param ly lower left y coordinate
     * @param ux upper right x coordinate
     * @param uy upper right y coordinate
     * @return Rectangle
     */
    public static Rectangle from(int lx, int ly, int ux, int uy) {
        return new Rectangle(new Point(lx, ly), new Point(ux, uy));
    }

    /**
     * Returns copy of lower left point
     *
     * @return Point
     */
    public Point getLowerLeft() {
        return new Point(lowerLeft);
    }

    /**
     * Returns copy of upper right point
     *
     * @return Point
     */
    public Point getUpperRight() {
        return new Point(upperRight);
    }

    /**
     * @return pair of immutable upper and lower horizontal lines
     */
    public List<Line> getHorizontal() {
        return Arrays.asList(
                new Line(new Point(lowerLeft), new Point(upperRight.getX(), lowerLeft.getY())),
                new Line(new Point(lowerLeft.getX(), upperRight.getY()), new Point(upperRight))
        );
    }

    /**
     * @return pair of immutable left and right vertical lines
     */
    public List<Line> getVertical() {
        return Arrays.asList(
                new Line(new Point(lowerLeft), new Point(lowerLeft.getX(), upperRight.getY())),
                new Line(new Point(upperRight.getX(), lowerLeft.getY()), new Point(upperRight))
        );
    }

    /**
     * @param other Rectangle
     * @return whether this rectangle contains other rectangle
     */
    public boolean contains(Rectangle other) {
        return RectangleUtil.contains(this, other.getLowerLeft()) &&
                RectangleUtil.contains(this, other.getUpperRight());
    }

    /**
     * @param other Rectangle
     * @return whether this rectangle intersects another rectangle
     */
    public boolean intersects(Rectangle other) {
        return !RectangleUtil.intersection(this, other).isEmpty();
    }

    /**
     * @param other Rectangle
     * @return Points of intersection if rectangles intersect
     */
    public Set<Point> intersection(Rectangle other) {
        return RectangleUtil.intersection(this, other);
    }

    /**
     * @param other Rectangle
     * @return whether this rectangle is adjacent to other
     */
    public boolean isAdjacent(Rectangle other) {
        return RectangleUtil.isAdjacent(this, other);
    }
}
