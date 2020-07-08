package chadb.nuvalence.rectangles.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RectangleServiceImpl implements RectangleService {
    @Override
    public Rectangle createRectangle(Integer lx, Integer ly, Integer ux, Integer uy) {
        return Rectangle.from(lx, ly, ux, uy);
    }

    @Override
    public RectangleComparison compare(Rectangle rect1, Rectangle rect2) {
        RectangleComparison.RectangleComparisonBuilder builder = RectangleComparison.builder();

        builder.rect1(rect1);
        builder.rect2(rect2);

        builder.contains(rect1.contains(rect2));
        builder.adjacent(rect1.isAdjacent(rect2));
        builder.intersection(rect1.intersection(rect2));
        builder.intersects(rect1.intersects(rect2));

        return builder.build();
    }
}
