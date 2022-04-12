package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
                Arguments.of("8", "a", Position.of(0, 0)),
                Arguments.of("5", "d", Position.of(3, 3)),
                Arguments.of("1", "h", Position.of(7, 7))
        );
    }

    @Test
    @DisplayName("toString 테스트")
    void testToString() {
        assertThat(Position.of("1", "a").toString()).isEqualTo("a1");
    }

    @Test
    @DisplayName("좌표값 변환 확인")
    void testRowAndColumn() {
        assertThat(Position.of("1", "a").row()).isEqualTo(7);
        assertThat(Position.of("1", "a").column()).isEqualTo(0);
    }
}
