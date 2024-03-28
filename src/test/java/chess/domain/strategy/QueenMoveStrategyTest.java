package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class QueenMoveStrategyTest {

    private static Stream<Arguments> queenCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("a1"), true),
                Arguments.of(Position.of("d4"), Position.of("b6"), true),
                Arguments.of(Position.of("d4"), Position.of("e5"), true),
                Arguments.of(Position.of("d4"), Position.of("g1"), true),
                Arguments.of(Position.of("d4"), Position.of("d1"), true),
                Arguments.of(Position.of("d4"), Position.of("a4"), true),
                Arguments.of(Position.of("d4"), Position.of("d8"), true),
                Arguments.of(Position.of("d4"), Position.of("h4"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("퀸의 이동 전략은 한 번에 수직 혹은 수평 혹은 대각선으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("queenCanMoveTestParameters")
    void bishopCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        QueenMoveStrategy queenMoveStrategy = new QueenMoveStrategy();
        boolean actualCanMove = queenMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
