package chadb.nuvalence.rectangles.service;

import chadb.nuvalence.rectangles.service.Rectangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangleTest {

    @Test
    void shouldBeEqual() {
        Rectangle rectangle1 = Rectangle.from(0, 0, 3, 3);
        Rectangle rectangle2 = Rectangle.from(0, 0, 3, 3);

        assertThat(rectangle1).isEqualTo(rectangle2);
        assertThat(rectangle1).isNotSameAs(rectangle2);
    }

    @Test
    void shouldBeCloneable() {
        Rectangle rectangle1 = Rectangle.from(0, 0, 3, 3);
        Rectangle rectangle2 = Rectangle.from(rectangle1);

        assertThat(rectangle1).isEqualTo(rectangle2);
        assertThat(rectangle1).isNotSameAs(rectangle2);
    }


}