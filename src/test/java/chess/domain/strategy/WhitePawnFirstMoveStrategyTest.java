package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WhitePawnFirstMoveStrategyTest {

    private static Stream<Arguments> whitePawnCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d5"), true),
                Arguments.of(Position.of("d4"), Position.of("d6"), true),
                Arguments.of(Position.of("d4"), Position.of("d7"), false),
                Arguments.of(Position.of("d4"), Position.of("d3"), false)
        );
    }

    @DisplayName("처음 움직이는 흰색 폰의 이동 전략은 1칸 혹은 2칸 위로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("whitePawnCanMoveTestParameters")
    void pawnCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        WhitePawnFirstMoveStrategy whitePawnFirstMoveStrategy = new WhitePawnFirstMoveStrategy();
        boolean actualCanMove = whitePawnFirstMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
