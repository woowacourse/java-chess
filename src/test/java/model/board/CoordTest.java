package model.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordTest {
    @Test
    void valueTest() {
        assertThat(Coord.of(5).val()).isEqualTo(5);
    }

    @Test
    void underflowTest() {
        assertThatThrownBy(() -> Coord.of(-1));
    }

    @Test
    void overflowTest() {
        assertThatThrownBy(() -> Coord.of(8));
    }

    @Test
    void toStringXTest() {
        assertThat(Coord.of(5).convertToStringX()).isEqualTo("f");
    }

    @Test
    void toStringYTest() {
        assertThat(Coord.of(5).convertToStringY()).isEqualTo("6");
    }

    @Test
    void toStringTest() {
        assertThat(Coord.of(5).toString()).isEqualTo("5");
    }

    @Test
    void sortTest() {
        List<Coord> testList = Arrays.asList(Coord.of(5), Coord.of(2), Coord.of(3));
        Collections.sort(testList);
        assertThat(testList).isEqualTo(Arrays.asList(Coord.of(2), Coord.of(3), Coord.of(5)));
    }

    @Test
    void equalityTest() {
        assertThat(Coord.of(7) == Coord.of(7));
    }
}