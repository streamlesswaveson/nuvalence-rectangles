package chadb.nuvalence.rectangles.service;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class RectangleComparison {
    private Rectangle rect1;
    private Rectangle rect2;
    private boolean intersects;
    private Set<Point> intersection;
    private boolean contains;
    private boolean adjacent;
}
