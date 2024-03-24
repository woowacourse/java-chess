package chess.domain;

import chess.domain.piece.Rook;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    private static Stream<Arguments> checkPieceMovePossibleParameters() {
        return Stream.of(
                Arguments.of(Position.of("e4"), Team.NONE, false),
                Arguments.of(Position.of("d8"), Team.BLACK, false),
                Arguments.of(Position.of("d8"), Team.WHITE, true)
        );
    }

    @DisplayName("말의 이동 경로에 다른 말이 있다면 이동 불가능하다.")
    @Test
    void pieceMoveFailByOtherPieceTest() {
        Position currentPosition = Position.of("d4");
        Position newPosition = Position.of("d8");
        Position obstaclePosition = Position.of("d6");

        Rook rook = new Rook(new PieceInfo(currentPosition, Team.WHITE));
        Rook obstacleRook = new Rook(new PieceInfo(obstaclePosition, Team.WHITE));

        Board board = new Board();
        board.placePiece(currentPosition, rook);
        board.placePiece(obstaclePosition, obstacleRook);

        boolean actualExistObstacle = board.checkObstacleInRange(currentPosition, newPosition);

        Assertions.assertThat(actualExistObstacle).isEqualTo(true);
    }

    @DisplayName("말이 없거나 다른 색깔의 말이 있다면 그 위치로 이동할 수 있지만, 같은 색깔의 말이 있다면 그 위치로 이동할 수 없다.")
    @ParameterizedTest
    @MethodSource("checkPieceMovePossibleParameters")
    void checkPieceMovePossibleTest(Position otherPosition, Team otherTeam, boolean expectedPieceMovePossible) {
        Position currentPosition = Position.of("d4");
        Team currentTeam = Team.WHITE;

        Rook rook = new Rook(new PieceInfo(currentPosition, currentTeam));
        Rook otherRook = new Rook(new PieceInfo(otherPosition, otherTeam));

        Board board = new Board();
        board.placePiece(currentPosition, rook);
        board.placePiece(otherPosition, otherRook);

        boolean actualPieceMovePossible = board.checkSameTeamPieceExist(currentTeam, otherPosition);

        Assertions.assertThat(actualPieceMovePossible).isEqualTo(expectedPieceMovePossible);
    }
}
