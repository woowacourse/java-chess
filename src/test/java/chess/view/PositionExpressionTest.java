package chess.view;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PositionExpressionTest {

    @ParameterizedTest
    @DisplayName("위치에 대한 문자열을 위치로 변환한다.")
    @MethodSource("positionExpressions")
    void mapToPosition(String positionExpression, Position expectedPosition) {
        Position actualPosition = PositionExpression.mapToPosition(positionExpression);

        assertThat(actualPosition).isEqualTo(expectedPosition);
    }

    static Stream<Arguments> positionExpressions() {
        return Stream.of(
                Arguments.of("a1", Position.of(File.A, Rank.ONE)),
                Arguments.of("h1", Position.of(File.H, Rank.ONE)),
                Arguments.of("a8", Position.of(File.A, Rank.EIGHT)),
                Arguments.of("h8", Position.of(File.H, Rank.EIGHT))
        );
    }

    @ParameterizedTest
    @DisplayName("올바르지 않은 형식이면 예외를 발생시킨다.")
    @ValueSource(strings = {"a0", "a9", "i1", "i8"})
    void validateFormatFail(String input) {
        assertThatCode(() -> PositionExpression.mapToPosition(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 형식의 위치 입력입니다.");
    }
}
