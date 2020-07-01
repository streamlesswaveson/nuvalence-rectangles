package chadb.nuvalence.rectangles;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void shouldBeEqual() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(1,2);
        assertThat(p1).isEqualTo(p2);

    }

}