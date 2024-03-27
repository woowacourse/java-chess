package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackPawnNotFirstMoveTest {

    private static Stream<Arguments> blackPawnMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), Position.of("d3")),
                Arguments.of(Position.of("d4"), Position.of("d2"), Position.of("d4")),
                Arguments.of(Position.of("d4"), Position.of("d5"), Position.of("d4"))
        );
    }

    @DisplayName("처음 움직이는 것이 아닌 검정 폰은 한 번에 한 칸 아래로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("blackPawnMoveTestParameters")
    void pawnMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(Position.of("d5"), Team.BLACK);
        Pawn blackPawnNotFirstMove = new Pawn(pieceInfo);
        blackPawnNotFirstMove = blackPawnNotFirstMove.move(currentPosition, false, false, false);
        Pawn movedPawn = blackPawnNotFirstMove.move(newPosition, false, false, false);

        Position actualMovedPosition = movedPawn.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
