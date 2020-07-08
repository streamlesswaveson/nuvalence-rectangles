package chadb.nuvalence.rectangles.service;

public interface RectangleService {
    Rectangle createRectangle(Integer lx, Integer ly, Integer ux, Integer uy);

    RectangleComparison compare(Rectangle rect1, Rectangle rect2);
}
