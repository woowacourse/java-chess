package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class King extends Piece {
    private static final int MOVE_DISTANCE = 1;

    public King(Team team) {
        super(team);
    }

    @Override
    boolean canNotMoveByItsOwnInPassing(Position start, Position destination) {
        return start.calculateDistance(destination) != MOVE_DISTANCE;
    }

    @Override
    boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard) {
        return false;
    }
}
