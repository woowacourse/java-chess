package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WhitePawnFirstMoveTest {

    private static Stream<Arguments> whitePawnMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d5"), Position.of("d5")),
                Arguments.of(Position.of("d4"), Position.of("d6"), Position.of("d6")),
                Arguments.of(Position.of("d4"), Position.of("d7"), Position.of("d4")),
                Arguments.of(Position.of("d4"), Position.of("d3"), Position.of("d4"))
        );
    }

    @DisplayName("처음 움직이는 흰색 폰은 1칸 혹은 2칸 위로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("whitePawnMoveTestParameters")
    void pawnMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.WHITE);
        Pawn whitePawnFirstMove = new Pawn(pieceInfo, new WhitePawnFirstMoveStrategy());
        Pawn movedPawn = whitePawnFirstMove.move(newPosition, false, false);

        Position actualMovedPosition = movedPawn.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
