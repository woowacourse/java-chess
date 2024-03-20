package chess.piece;

import chess.board.Position;

public class Queen extends Piece {

    private static final int MAX_UNIT_MOVE = 8;

    public Queen(Color color) {
        super(PieceType.QUEEN, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isOnPositiveSlopeDiagonal(destination) ||
                source.isOnNegativeSlopeDiagonal(destination) ||
                source.isOnSameFile(destination) ||
                source.isOnSameRank(destination);
    }
}
