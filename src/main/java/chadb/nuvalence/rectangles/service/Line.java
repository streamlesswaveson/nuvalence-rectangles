package chadb.nuvalence.rectangles.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Line {
    private final Point a;
    private final Point b;

    public Point getA() {
        return new Point(a);
    }

    public Point getB() {
        return new Point(b);
    }
}
