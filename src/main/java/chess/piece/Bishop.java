package chess.piece;

import chess.board.Position;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 8;

    public Bishop(Color color) {
        super(PieceType.BISHOP, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isOnPositiveSlopeDiagonal(destination) ||
                source.isOnNegativeSlopeDiagonal(destination);
    }
}
