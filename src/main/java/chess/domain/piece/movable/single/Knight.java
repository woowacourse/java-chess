package chess.domain.piece.movable.single;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public final class Knight extends SingleMovablePiece {

    private static final PieceType PIECE_TYPE = PieceType.KNIGHT;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.KNIGHT;
    private static final Knight KNIGHT = new Knight();

    private Knight() {
        super(PIECE_TYPE, PIECE_DIRECTIONS);
    }

    public static Knight getInstance() {
        return KNIGHT;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
