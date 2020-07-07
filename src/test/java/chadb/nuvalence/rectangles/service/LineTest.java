package chadb.nuvalence.rectangles.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chadb.nuvalence.rectangles.service.ShapeFactory.lineFromString;
import static chadb.nuvalence.rectangles.service.ShapeFactory.pointFromString;
import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {

    @CsvSource(delimiter = '|',
            value = {
                    "0,0,0,3|-1,1,3,1|0,1",
                    "-1,1,3,1|0,0,0,3|0,1",
                    "-2,1,4,1|2,-3,2,3|2,1"
            }
    )
    @ParameterizedTest
    void shouldFindLineIntersect(String l1, String l2, String p1) {
        Line line1 = lineFromString(l1);
        Line line2 = lineFromString(l2);

        Point expected = pointFromString(p1);
        Point point = RectangleUtil.intersect(line1, line2);
        assertThat(point).isEqualTo(expected);
    }

    @CsvSource(delimiter = '|',
            value = {
                    //xaxis
                    "-2,0,1,0|0,0,3,0|true", //partial
                    "0,0,3,0|-2,0,1,0|true", // partial converse
                    "-2,0,1,0|-2,0,1,0|true", // same line
                    "0,0,3,0|1,0,2,0|true", // subline xaxis
                    "1,0,2,0|0,0,3,0|true", // subline xaxis converse
                    "-2,1,1,1|0,0,3,0|false", // parallel line xaxis
                    "0,0,3,0|-2,1,1,1|false", // parallel line xaxis converse

                    //yaxis
                    "0,-2,0,1|0,0,0,3|true", //partial
                    "0,0,0,3|0,-2,0,1|true", // partial converse
                    "0,-2,0,1|0,-2,0,1|true", // same line
                    "0,0,0,3|0,1,0,2|true", // subline yaxis
                    "0,1,0,2|0,0,0,3|true", // subline yaxis converse
                    "1,-2,1,1|0,0,0,3|false", // parallel line yaxis
                    "0,0,0,3|1,-2,1,1|false", // parallel line xaxis converse

            }
    )
    @ParameterizedTest
    void shouldFindLineAdjacency(String l1, String l2, boolean expected) {
        Line line1 = lineFromString(l1);
        Line line2 = lineFromString(l2);

        assertThat(RectangleUtil.adjacent(line1, line2)).isEqualTo(expected);
    }
}
