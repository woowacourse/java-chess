package chess.view;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionExpressionTest {

    @DisplayName("위치에 대한 문자열을 위치로 변환한다.")
    @ParameterizedTest
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
}
