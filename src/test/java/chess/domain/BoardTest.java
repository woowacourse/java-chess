package chess.domain;

import chess.domain.piece.Rook;
import chess.domain.strategy.RookMoveStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    
    @DisplayName("말의 이동 경로에 다른 말이 있다면 이동 불가능하다.")
    @Test
    void pieceMoveFailByOtherPieceTest() {
        Position currentPosition = Position.of("d4");
        Position newPosition = Position.of("d8");
        Position obstaclePosition = Position.of("d6");

        Rook rook = new Rook(new PieceInfo(currentPosition, Team.WHITE), new RookMoveStrategy());
        Rook obstacleRook = new Rook(new PieceInfo(obstaclePosition, Team.WHITE), new RookMoveStrategy());

        Board board = new Board();
        board.placePiece(currentPosition, rook);
        board.placePiece(obstaclePosition, obstacleRook);

        boolean actualExistObstacle = board.checkObstacleInRange(currentPosition, newPosition);

        Assertions.assertThat(actualExistObstacle).isEqualTo(true);
    }
}
