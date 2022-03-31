package chess.domain.piece.movable.multiple;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public final class Bishop extends MultipleMovablePiece {

    private static final PieceType PIECE_TYPE = PieceType.BISHOP;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.BISHOP;
    private static final Bishop BISHOP = new Bishop();

    private Bishop() {
        super(PIECE_TYPE, PIECE_DIRECTIONS);
    }

    public static Bishop getInstance() {
        return BISHOP;
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
