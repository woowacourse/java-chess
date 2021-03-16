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
        Position position1 = new Position(Row.FIFTH, Column.A);
        Position position2 = new Position(Row.FIFTH, Column.A);

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
                Arguments.of(new Position(Row.FIRST, Column.A), new Position(Row.FIRST, Column.B)),
                Arguments.of(new Position(Row.FIRST, Column.A), new Position(Row.SECOND, Column.A))
        );
    }
}
