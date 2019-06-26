package model.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordinateTest {
    @Test
    void valueTest() {
        assertThat(Coordinate.of(5).value()).isEqualTo(5);
    }

    @Test
    void underflowTest() {
        assertThatThrownBy(() -> Coordinate.of(-1));
    }

    @Test
    void overflowTest() {
        assertThatThrownBy(() -> Coordinate.of(8));
    }

    @Test
    void toStringXTest() {
        assertThat(Coordinate.of(5).convertToStringX()).isEqualTo("f");
    }

    @Test
    void toStringYTest() {
        assertThat(Coordinate.of(5).convertToStringY()).isEqualTo("6");
    }

    @Test
    void toStringTest() {
        assertThat(Coordinate.of(5).toString()).isEqualTo("5");
    }

    @Test
    void equalityTest() {
        assertThat(Coordinate.of(7) == Coordinate.of(7));
    }

    @Test
    void sortTest() {
        assertThat(
                Stream.of(Coordinate.of(5), Coordinate.of(2), Coordinate.of(3))
                        .sorted()
        ).isEqualTo(
                Arrays.asList(Coordinate.of(2), Coordinate.of(3), Coordinate.of(5))
        );
    }
}