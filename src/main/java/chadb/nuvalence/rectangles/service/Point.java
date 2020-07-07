package chadb.nuvalence.rectangles.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Point {
    private final int x;
    private final int y;

    /**
     * Copy constructor
     *
     * @param p
     */
    public Point(Point p) {
        this(p.x, p.y);
    }
}
