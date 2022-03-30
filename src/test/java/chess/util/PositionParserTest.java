package chess.util;

import chess.chessgame.Position;
import chess.utils.PositionParser;
import org.apache.commons.lang3.tuple.Pair;
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
    void wrongPositionInput(char x, char y) {
        assertThatThrownBy(() -> PositionParser.parse(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("올바르지 않은 위치 정보입니다.");
    }

    @ParameterizedTest
    @MethodSource("positionInput")
    @DisplayName("유효한 위치 입력시 입력을 올바른 인덱스 값으로 매핑하여 pair로 반환")
    void rightPositionInput(char x, char y, Pair<Integer, Integer> expected) {
        Position position = PositionParser.parse(x, y);
        assertThat(position.getX()).isEqualTo(expected.getRight());
        assertThat(position.getY()).isEqualTo(expected.getLeft());
    }

    private static Stream<Arguments> positionInput() {
        return Stream.of(
                Arguments.of('a', '8', Pair.of(0, 0)),
                Arguments.of('d', '5', Pair.of(3, 3)),
                Arguments.of('h', '1', Pair.of(7, 7))
        );
    }
}
