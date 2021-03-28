package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("position이 잘 생성되는지 확인")
    void createPosition() {
        assertThatCode(() -> Position.of(Column.A, Row.ONE))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("positions")
    @DisplayName("position이 주어진 위치로 잘 이동하는지 확인")
    void moveBy(int column, int row, Position expected) {
        final Position position = Position.of(Column.D, Row.SIX);
        assertThat(position.moveBy(column, row)).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스판 position들이 잘 캐싱되는지 확인")
    void cache() {
        final Position position1 = Position.from("a1");
        final Position position2 = Position.of(Column.A, Row.ONE);
        assertThat(position1).isSameAs(position2);
    }
}
