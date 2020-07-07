package chadb.nuvalence.rectangles.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RectangleDTO {
    private final PointDTO lowerLeft;
    private final PointDTO upperRight;

}
