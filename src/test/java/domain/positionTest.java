package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class positionTest {

    private static Stream<Arguments> positions() {
        return Stream.of(
                Arguments.of(-3, -5, new Position(Column.A, Row.ONE)),
                Arguments.of(-2, 2, new Position(Column.B, Row.EIGHT)),
                Arguments.of(0, 2, new Position(Column.D, Row.EIGHT)),
                Arguments.of(4, 0, new Position(Column.H, Row.SIX))
        );
    }

    @Test
    void createPosition() {
        assertThatCode(() -> new Position(Column.A, Row.ONE))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("positions")
    void moveBy(int column, int row, Position expected) {
        Position position = new Position(Column.D, Row.SIX);
        assertThat(position.moveBy(column, row)).isEqualTo(expected);
    }
}
