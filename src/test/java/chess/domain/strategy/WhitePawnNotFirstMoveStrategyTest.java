package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WhitePawnNotFirstMoveStrategyTest {

    private static Stream<Arguments> whitePawnCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d5"), true),
                Arguments.of(Position.of("d4"), Position.of("d6"), false),
                Arguments.of(Position.of("d4"), Position.of("d3"), false)
        );
    }

    @DisplayName("처음 움직이는 것이 아닌 흰색 폰의 이동 전략은 한 번에 한 칸 아래로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("whitePawnCanMoveTestParameters")
    void pawnCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        WhitePawnNotFirstMoveStrategy whitePawnNotFirstMoveStrategy = new WhitePawnNotFirstMoveStrategy();
        boolean actualCanMove = whitePawnNotFirstMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
