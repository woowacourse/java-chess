package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("Position 같은 좌표 비교")
    void equalsSucceed() {
        Position position1 = new Position(Column.A, Row.FIFTH);
        Position position2 = new Position(Column.A, Row.FIFTH);

        assertThat(position1).isEqualTo(position2);
    }

    @ParameterizedTest
    @DisplayName("Position 다른 좌표 비교")
    @MethodSource("equalsFailTestcase")
    void equalsFail(Position position1, Position position2) {
        assertThat(position1).isNotEqualTo(position2);
    }

    private static Stream<Arguments> equalsFailTestcase() {
        return Stream.of(
                Arguments.of(new Position(Column.A, Row.FIRST), new Position(Column.B, Row.FIRST)),
                Arguments.of(new Position(Column.A, Row.FIRST), new Position(Column.A, Row.SECOND))
        );
    }
}
