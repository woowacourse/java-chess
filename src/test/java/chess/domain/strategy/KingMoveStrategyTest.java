package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KingMoveStrategyTest {

    private static Stream<Arguments> kingCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), true),
                Arguments.of(Position.of("d4"), Position.of("d5"), true),
                Arguments.of(Position.of("d4"), Position.of("c4"), true),
                Arguments.of(Position.of("d4"), Position.of("e4"), true),
                Arguments.of(Position.of("d4"), Position.of("c3"), true),
                Arguments.of(Position.of("d4"), Position.of("c5"), true),
                Arguments.of(Position.of("d4"), Position.of("e3"), true),
                Arguments.of(Position.of("d4"), Position.of("e5"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("킹의 이동 전략은 한 번에 수직 혹은 수평 혹은 대각선으로 한 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("kingCanMoveTestParameters")
    void kingCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        KingMoveStrategy kingMoveStrategy = new KingMoveStrategy();
        boolean actualCanMove = kingMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
