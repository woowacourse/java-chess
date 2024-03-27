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

public class BlackPawnFirstMoveTest {

    private static Stream<Arguments> pawnStraightMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d3"), Position.of("d3")),
                Arguments.of(Position.of("d4"), Position.of("d2"), Position.of("d2")),
                Arguments.of(Position.of("d4"), Position.of("d1"), Position.of("d4")),
                Arguments.of(Position.of("d4"), Position.of("d5"), Position.of("d4"))
        );
    }

    private static Stream<Arguments> pawnDiagonalMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("c3"), false, true, false, Position.of("c3")),
                Arguments.of(Position.of("d4"), Position.of("c3"), false, true, true, Position.of("d4")),
                Arguments.of(Position.of("d4"), Position.of("c3"), false, false, true, Position.of("d4")),
                Arguments.of(Position.of("d4"), Position.of("c3"), false, false, false, Position.of("d4"))
        );
    }

    @DisplayName("처음 움직이는 검정 폰은 1칸 혹은 2칸 아래로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("pawnStraightMoveTestParameters")
    void pawnStraightMoveTest(Position currentPosition, Position newPosition, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.BLACK);
        Pawn blackPawnFirstMove = new Pawn(pieceInfo);
        Pawn movedPawn = blackPawnFirstMove.move(newPosition, false, false, false);

        Position actualMovedPosition = movedPawn.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }

    @DisplayName("처음 움직이는 검정 폰은 전방 대각선에 상대 말이 있다면 해당 위치로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("pawnDiagonalMoveTestParameters")
    void pawnDiagonalMoveTest(Position currentPosition, Position newPosition, boolean isDisturbed,
                              boolean isOtherPieceExist, boolean isSameTeam, Position expectedMovedPosition) {
        PieceInfo pieceInfo = new PieceInfo(currentPosition, Team.BLACK);
        Pawn blackPawnFirstMove = new Pawn(pieceInfo);
        Pawn movedPawn = blackPawnFirstMove.move(newPosition, isDisturbed, isOtherPieceExist, isSameTeam);

        Position actualMovedPosition = movedPawn.getPosition();

        Assertions.assertThat(actualMovedPosition).isEqualTo(expectedMovedPosition);
    }
}
