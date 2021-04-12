package chess.domain.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PositionTest {

    private static Stream<Arguments> positions() {
        return Stream.of(
                Arguments.of(-3, -5, Position.of(Column.A, Row.ONE)),
                Arguments.of(-2, 2, Position.of(Column.B, Row.EIGHT)),
                Arguments.of(0, 2, Position.of(Column.D, Row.EIGHT)),
                Arguments.of(4, 0, Position.of(Column.H, Row.SIX))
        );
    }

    @Test
    void createPosition() {
        assertThatCode(() -> Position.of(Column.A, Row.ONE))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("positions")
    void moveBy(int column, int row, Position expected) {
        Position position = Position.of(Column.D, Row.SIX);
        assertThat(position.move(column, row)).isEqualTo(expected);
    }

    @Test
    void cache() {
        Position position1 = Position.from("a1");
        Position position2 = Position.of(Column.A, Row.ONE);
        assertThat(position1).isSameAs(position2);
    }

    @Test
    void toKey() {
        assertThat(Position.from("c2")
                           .toKey()).isEqualTo("c2");

    }
}
