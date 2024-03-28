package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BishopMoveStrategyTest {

    private static Stream<Arguments> bishopCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("a1"), true),
                Arguments.of(Position.of("d4"), Position.of("b6"), true),
                Arguments.of(Position.of("d4"), Position.of("e5"), true),
                Arguments.of(Position.of("d4"), Position.of("g1"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("비숍의 이동 전략은 한 번에 대각선으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("bishopCanMoveTestParameters")
    void bishopCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy();
        boolean actualCanMove = bishopMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
