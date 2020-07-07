package chadb.nuvalence.rectangles.api.model;

import lombok.Getter;

import java.util.Set;

@Getter
public class RectangleComparisonDTO {
    private RectangleDTO rect1;
    private RectangleDTO rect2;
    private boolean intersects;
    private Set<PointDTO> intersection;
    private boolean contains;
    private boolean adjacent;

}
