package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PathCheckTest {
    @DisplayName("a1에서 a3 사이에는 a2가 있다.")
    @Test
    void path_a1_a3() {
        assertThat(PathCheck.check(Position.of(Column.A, Row.ONE),
            Position.of(Column.A, Row.THREE),
            (position) -> Objects.equals(position, Position.of(Column.A, Row.TWO)))).isTrue();

    }

    @DisplayName("a1에서 c1 사이에는 b1이 있다.")
    @Test
    void path_a1_c1() {
        assertThat(PathCheck.check(Position.of(Column.A, Row.ONE),
            Position.of(Column.C, Row.ONE),
            (position) -> Objects.equals(position, Position.of(Column.B, Row.ONE)))).isTrue();

    }

    @DisplayName("e1에서 c3 사이에는 d2가 있다.")
    @Test
    void path_e1_c3() {
        assertThat(PathCheck.check(Position.of(Column.E, Row.ONE),
            Position.of(Column.C, Row.THREE),
            (position) -> Objects.equals(position, Position.of(Column.D, Row.TWO)))).isTrue();
    }
}
