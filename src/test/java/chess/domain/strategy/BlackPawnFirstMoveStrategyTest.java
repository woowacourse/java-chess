package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackPawnFirstMoveStrategyTest {

    private static Stream<Arguments> blackPawnCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), true),
                Arguments.of(Position.of("d4"), Position.of("d2"), true),
                Arguments.of(Position.of("d4"), Position.of("d1"), false),
                Arguments.of(Position.of("d4"), Position.of("d5"), false)
        );
    }

    @DisplayName("처음 움직이는 검정 폰의 이동 전략은 1칸 혹은 2칸 아래로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("blackPawnCanMoveTestParameters")
    void pawnCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        BlackPawnFirstMoveStrategy blackPawnFirstMoveStrategy = new BlackPawnFirstMoveStrategy();
        boolean actualCanMove = blackPawnFirstMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
