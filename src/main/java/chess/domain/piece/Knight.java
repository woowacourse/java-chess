package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Knight extends Piece {
    private static final int L_SHAPE_SQUARED_DISTANCE = 5;

    public Knight(Team team) {
        super(team);
    }

    @Override
    boolean canNotMoveByItsOwnInPassing(Position start, Position destination) {
        return start.squaredDistanceWith(destination) != L_SHAPE_SQUARED_DISTANCE;
    }

    @Override
    boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard) {
        return false;
    }
}
