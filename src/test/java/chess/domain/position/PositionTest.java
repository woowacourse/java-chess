package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @MethodSource("positionInput")
    @DisplayName("유효한 위치 입력시 position 객체 생성")
    void rightPositionInput(String x, String y, Position expected) {
        Position position = Position.of(x, y);
        assertThat(position).isEqualTo(expected);
    }

    private static Stream<Arguments> positionInput() {
        return Stream.of(
                Arguments.of("8", "a", new Position(0, 0)),
                Arguments.of("5", "d", new Position(3, 3)),
                Arguments.of("1", "h", new Position(7, 7))
        );
    }
}
