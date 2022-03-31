package chess.domain.piece.movable.single;

import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public final class King extends SingleMovablePiece {

    private static final PieceType PIECE_TYPE = PieceType.KING;
    private static final PieceDirections PIECE_DIRECTIONS = PieceDirections.KING;
    private static final King KING = new King();

    private King() {
        super(PIECE_TYPE, PIECE_DIRECTIONS);
    }

    public static King getInstance() {
        return KING;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
