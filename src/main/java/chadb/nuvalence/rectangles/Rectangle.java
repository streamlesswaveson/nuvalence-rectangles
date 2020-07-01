package chadb.nuvalence.rectangles;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Rectangle {
    private final Point lowerLeft;
    private final Point upperRight;

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
}
