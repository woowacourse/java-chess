package chess.domain.piece.movable.multiple;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public final class Queen extends MultipleMovablePiece {

    private static final PieceType PIECE_TYPE = PieceType.QUEEN;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.QUEEN;
    private static final Queen QUEEN = new Queen();

    private Queen() {
        super(PIECE_TYPE, PIECE_DIRECTIONS);
    }

    public static Queen getInstance() {
        return QUEEN;
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
