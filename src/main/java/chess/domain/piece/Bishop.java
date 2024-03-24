package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    boolean canNotMoveByItsOwnInPassing(Position start, Position destination) {
        return !start.isDiagonalWith(destination);
    }

    @Override
    boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard) {
        return chessBoard.isPathHasObstacle(start.calculateSlidingPath(destination));
    }
}
