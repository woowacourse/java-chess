package chess.domain.piece.movable.multiple;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public final class Rook extends MultipleMovablePiece {

    private static final PieceType PIECE_TYPE = PieceType.ROOK;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.ROOK;
    private static final Rook ROOK = new Rook();

    private Rook() {
        super(PIECE_TYPE, PIECE_DIRECTIONS);
    }

    public static Rook getInstance() {
        return ROOK;
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