package chess.piece;

import chess.board.Position;

public class Rook extends Piece {

    private static final int MAX_UNIT_MOVE = 8;

    public Rook(Color color) {
        super(PieceType.ROOK, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isOnSameFile(destination) || source.isOnSameRank(destination);
    }
}
