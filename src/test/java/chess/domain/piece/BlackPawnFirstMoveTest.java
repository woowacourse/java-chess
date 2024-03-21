package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.BlackPawnFirstMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackPawnFirstMoveTest {

    private static Stream<Arguments> blackPawnMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), Position.of("d3")),
                Arguments.of(Position.of("d4"), Position.of("d2"), Position.of("d2")),
                Arguments.of(Position.of("d4"), Position.of("d1"), Position.of("d4")),
                Arguments.of(Position.of("d4"), Position.of("d5"), Position.of("d4"))
        );
    }

    @DisplayName("처음 움직이는 검정 폰은 1칸 혹은 2칸 아래로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("blackPawnMoveTestParameters")
    void pawnMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.BLACK);
        Pawn blackPawnFirstMove = new Pawn(pieceInfo, new BlackPawnFirstMoveStrategy());
        Pawn movedPawn = blackPawnFirstMove.move(newPosition, false, false);

        Position actualMovedPosition = movedPawn.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
