package chadb.nuvalence.rectangles;

import java.util.stream.Stream;

public class ShapeFactory {
    static int[] intFromString(String s) {
        return Stream.of(s.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    static Point pointFromString(String p1) {
        int[] ints = intFromString(p1);
        return new Point(ints[0], ints[1]);
    }

    static Rectangle rectFromString(String s1) {
        int[] ints = intFromString(s1);
        return Rectangle.from(ints[0], ints[1], ints[2], ints[3]);
    }

    static Line lineFromString(String l) {
        int[] ints = intFromString(l);
        return new Line(new Point(ints[0], ints[1]), new Point(ints[2], ints[3]));
    }

}
