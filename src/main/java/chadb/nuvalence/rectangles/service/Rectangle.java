package chadb.nuvalence.rectangles.service;

import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
public class Rectangle {
    private final Point lowerLeft;
    private final Point upperRight;

    public Rectangle(Point ll, Point ur) {
        if (ll.getX() > ur.getX()) throw new IllegalArgumentException("Left x must be less than right x");
        if (ll.getY() > ll.getY()) throw new IllegalArgumentException("Left y must be less than right y");
        this.lowerLeft = ll;
        this.upperRight = ur;
    }

    public static Rectangle from(Rectangle origin) {
        return Rectangle.from(origin.getLowerLeft(), origin.getUpperRight());
    }

    public static Rectangle from(Point ll, Point ur) {
        return new Rectangle(new Point(ll), new Point(ur));
    }

    public static Rectangle from(int lx, int ly, int ux, int uy) {
        return new Rectangle(new Point(lx, ly), new Point(ux, uy));
    }

    public Point getLowerLeft() {
        return new Point(lowerLeft);
    }

    public Point getUpperRight() {
        return new Point(upperRight);
    }

    public List<Line> getHorizontal() {
        return Arrays.asList(
                new Line(new Point(lowerLeft), new Point(upperRight.getX(), lowerLeft.getY())),
                new Line(new Point(lowerLeft.getX(), upperRight.getY()), new Point(upperRight))
        );
    }

    public List<Line> getVertical() {
        return Arrays.asList(
                new Line(new Point(lowerLeft), new Point(lowerLeft.getX(), upperRight.getY())),
                new Line(new Point(upperRight.getX(), lowerLeft.getY()), new Point(upperRight))
        );
    }

    public boolean contains(Rectangle other) {
        return RectangleUtil.contains(this, other.getLowerLeft()) &&
                RectangleUtil.contains(this, other.getUpperRight());
    }

    public boolean intersects(Rectangle other) {
        return !RectangleUtil.intersection(this, other).isEmpty();
    }

    public Set<Point> intersection(Rectangle other) {
        return RectangleUtil.intersection(this, other);
    }

    public boolean isAdjacent(Rectangle other) {
        return RectangleUtil.isAdjacent(this, other);
    }
}
