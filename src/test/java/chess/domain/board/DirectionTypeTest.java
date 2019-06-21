package chess.domain.board;

import org.junit.jupiter.api.Test;

import static chess.domain.board.DirectionType.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class DirectionTypeTest {
    @Test
    void valueOf_00to50_EAST() {
        Point prev = Point.of(0, 0);
        Point next = Point.of(5, 0);
        assertThat(valueOf(prev, next)).isEqualTo(EAST);
    }

    @Test
    void valueOf_00to05_SOUTH() {
        Point prev = Point.of(0, 0);
        Point next = Point.of(0, 5);
        assertThat(valueOf(prev, next)).isEqualTo(SOUTH);
    }

    @Test
    void valueOf_00to55_SOUTHEAST() {
        Point prev = Point.of(0, 0);
        Point next = Point.of(5, 5);
        assertThat(valueOf(prev, next)).isEqualTo(SOUTHEAST);
    }

    @Test
    void valueOf_55to05_WEST() {
        Point prev = Point.of(5, 5);
        Point next = Point.of(0, 5);
        assertThat(valueOf(prev, next)).isEqualTo(WEST);
    }

    @Test
    void valueOf_55to50_NORTH() {
        Point prev = Point.of(5, 5);
        Point next = Point.of(5, 0);
        assertThat(valueOf(prev, next)).isEqualTo(NORTH);
    }

    @Test
    void valueOf_55to00_NORTHWEST() {
        Point prev = Point.of(5, 5);
        Point next = Point.of(0, 0);
        assertThat(valueOf(prev, next)).isEqualTo(NORTHWEST);
    }

    @Test
    void valueOf_55to66_NORTHEAST() {
        Point prev = Point.of(5, 5);
        Point next = Point.of(6, 4);
        assertThat(valueOf(prev, next)).isEqualTo(NORTHEAST);
    }

    @Test
    void valueOf_55to_SOUTHWEST() {
        Point prev = Point.of(5, 5);
        Point next = Point.of(4, 6);
        assertThat(valueOf(prev, next)).isEqualTo(SOUTHWEST);
    }

    @Test
    void valueOf_NotExistValue_IllegalArgumentException() {
        assertThatThrownBy(() -> valueOf(Point.of(1, 1), Point.of(2, 3))).isInstanceOf(IllegalArgumentException.class);
    }
}
