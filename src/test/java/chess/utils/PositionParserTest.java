package chess.utils;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionParserTest {

    @ParameterizedTest
    @CsvSource(value = {"1:a", "z:1", "a:9", "!:?", ",:."}, delimiter = ':')
    @DisplayName("유효하지 않은 위치 입력시 예외 발생")
    void wrongPositionInput(String x, String y) {
        assertThatThrownBy(() -> PositionParser.parse(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바르지 않은 위치 정보입니다.");
    }

    @ParameterizedTest
    @MethodSource("positionInput")
    @DisplayName("유효한 위치 입력시 입력을 올바른 인덱스 값으로 매핑하여 pair로 반환")
    void rightPositionInput(String x, String y, Position expected) {
        Position position = PositionParser.parse(x, y);
        assertThat(position).isEqualTo(expected);
    }

    private static Stream<Arguments> positionInput() {
        return Stream.of(
                Arguments.of("a", "8", new Position(0, 0)),
                Arguments.of("d", "5", new Position(3, 3)),
                Arguments.of("h", "1", new Position(7, 7))
        );
    }
}
