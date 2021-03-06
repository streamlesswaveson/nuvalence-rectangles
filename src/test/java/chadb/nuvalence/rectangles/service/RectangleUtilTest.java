package chadb.nuvalence.rectangles.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static chadb.nuvalence.rectangles.service.ShapeFactory.pointFromString;
import static chadb.nuvalence.rectangles.service.ShapeFactory.rectFromString;
import static org.assertj.core.api.Assertions.assertThat;

public class RectangleUtilTest {

    @CsvSource(delimiter = '|', value = {
            "0,0,3,3|-3,-3,1,1|0,1|1,0",
            "0,0,2,3|-1,1,1,2|0,1|0,2",
            "0,0,2,3|-1,2,1,4|0,2|1,3"
    })
    @ParameterizedTest
    void shouldIntersectTwoPoints(String s1, String s2, String p1, String p2) {
        Rectangle r1 = rectFromString(s1);
        Rectangle r2 = rectFromString(s2);

        assertThat(r1.intersects(r2)).isTrue();
        assertThat(r2.intersects(r1)).isTrue();

        Set<Point> points = RectangleUtil.intersection(r1, r2);
        assertThat(points).isNotEmpty().hasSize(2);
        Point e1 = pointFromString(p1);
        Point e2 = pointFromString(p2);

        assertThat(points).contains(e1);
        assertThat(points).contains(e2);
    }

    @CsvSource(delimiter = '|', value = {
            "0,0,2,3|-1,1,3,2|0,1|0,2|2,1|2,2",
            "0,0,3,3|1,-2,2,4|1,0|2,0|1,3|2,3"
    })
    @ParameterizedTest
    void shouldIntersectFourPoints(String s1, String s2, String p1, String p2, String p3, String p4) {

        Rectangle r1 = rectFromString(s1);
        Rectangle r2 = rectFromString(s2);

        assertThat(r1.intersects(r2)).isTrue();
        assertThat(r2.intersects(r1)).isTrue();

        Set<Point> points = RectangleUtil.intersection(r1, r2);
        assertThat(points).isNotEmpty().hasSize(4);

        Point e1 = pointFromString(p1);
        Point e2 = pointFromString(p2);
        Point e3 = pointFromString(p3);
        Point e4 = pointFromString(p4);

        assertThat(points).contains(e1);
        assertThat(points).contains(e2);
        assertThat(points).contains(e3);
        assertThat(points).contains(e4);
    }

    @CsvSource(delimiter = '|', value = {
            "0,0,3,3|0,0|true",
            "0,0,3,3|-1,0|false",
            "0,0,3,3|1,1|true",
            "0,0,3,3|1,3|true",
            "-4,-4,-1,-1|-1,2|false",
            "-4,-4,-1,-1|-1,-1|true",
            "-4,-4,-1,-1|-1,-3|true",
            "-4,-4,-1,-1|-1,-4|true",
            "-4,-4,-1,-1|-1,-5|false",
    })
    @ParameterizedTest
    void shouldContainPoint(String r, String p, boolean expected) {
        Rectangle re = rectFromString(r);
        Point point = pointFromString(p);

        assertThat(RectangleUtil.contains(re, point)).isEqualTo(expected);
    }

    @CsvSource(delimiter = '|', value = {
            "0,0,3,3|1,1,2,2|true",
            "0,0,3,3|0,0,2,1|true",
            "-3,-3,3,3|-2,1,2,3|true"
    })
    @ParameterizedTest
    void shouldContain(String s1, String s2, boolean expected) {
        Rectangle rect1 = rectFromString(s1);
        Rectangle rect2 = rectFromString(s2);

        assertThat(rect1.contains(rect2)).isEqualTo(expected);
        assertThat(rect2.contains(rect1)).isEqualTo(!expected);
    }

    @CsvSource(delimiter = '|', value = {
            "0,0,3,3|-3,-3,0,0",
            "0,0,3,3|0,3,3,6",
//            "-3,-3,3,3|-2,1,2,3"
    })
    @ParameterizedTest
    void shouldNotContain(String s1, String s2) {
        Rectangle rect1 = rectFromString(s1);
        Rectangle rect2 = rectFromString(s2);

        assertThat(rect1.contains(rect2)).isFalse();
    }

    @CsvSource(delimiter = '|', value = {
            "0,0,1,3|1,1,3,2", //ok
            "0,0,1,3|-1,-1,2,0",
            "0,0,1,3|-1,3,2,4",
            "0,0,1,3|-2,1,0,2"
    })
    @ParameterizedTest
    void shouldBeAdjacentSubline(String s1, String s2) {
        Rectangle rect1 = rectFromString(s1);
        Rectangle rect2 = rectFromString(s2);

        assertThat(rect1.contains(rect2)).isFalse();
        assertThat(rect2.contains(rect1)).isFalse();

        assertThat(rect1.intersects(rect2)).isFalse();
        assertThat(rect2.intersects(rect1)).isFalse();

        assertThat(RectangleUtil.isAdjacent(rect1, rect2)).isTrue();

    }

    @CsvSource(delimiter = '|', value = {
            "0,0,1,3|0,3,1,4",
            "0,3,1,4|0,0,1,3",
            "0,0,1,3|1,0,2,3",
            "1,0,2,3|0,0,1,3",
            "-1,0,0,3|0,0,1,3",
            "0,-1,1,0|0,0,1,3"
    })
    @ParameterizedTest
    void shouldBeAdjacentProper(String s1, String s2) {
        Rectangle rect1 = rectFromString(s1);
        Rectangle rect2 = rectFromString(s2);

        assertThat(rect1.contains(rect2)).isFalse();
        assertThat(rect2.contains(rect1)).isFalse();

        assertThat(rect1.intersects(rect2)).isFalse();
        assertThat(rect2.intersects(rect1)).isFalse();

        assertThat(RectangleUtil.isAdjacent(rect1, rect2)).isTrue();

    }

    @CsvSource(delimiter = '|', value = {
            "0,0,1,3|1,1,2,4",
            "0,0,1,3|-3,3,1,4",
            "0,0,1,3|-1,-1,0,3",
            "0,0,1,3|0,-1,3,0"
    })
    @ParameterizedTest
    void shouldBeAdjacentPartial(String s1, String s2) {
        Rectangle rect1 = rectFromString(s1);
        Rectangle rect2 = rectFromString(s2);

        assertThat(rect1.contains(rect2)).isFalse();
        assertThat(rect2.contains(rect1)).isFalse();

        assertThat(rect1.intersects(rect2)).isFalse();
        assertThat(rect2.intersects(rect1)).isFalse();

        assertThat(RectangleUtil.isAdjacent(rect1, rect2)).isTrue();

    }

    @CsvSource(delimiter = '|', value = {
            "0,0,1,3|2,0,3,3",
            "0,0,1,3|-3,4,1,5",
            "0,0,1,3|-3,-3,4,-2",
    })
    @ParameterizedTest
    void shouldNotBeAdjacent(String s1, String s2) {
        Rectangle rect1 = rectFromString(s1);
        Rectangle rect2 = rectFromString(s2);

        assertThat(rect1.contains(rect2)).isFalse();
        assertThat(rect2.contains(rect1)).isFalse();

        assertThat(rect1.intersects(rect2)).isFalse();
        assertThat(rect2.intersects(rect1)).isFalse();

        assertThat(RectangleUtil.isAdjacent(rect1, rect2)).isFalse();

    }

}
