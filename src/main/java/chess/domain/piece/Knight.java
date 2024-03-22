package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Knight extends Piece {
    private static final int L_SHAPE_DISTANCE = 5;

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        return start.squaredDistanceWith(destination) == L_SHAPE_DISTANCE;
    }
}
