package chess.domain.piece;

import chess.domain.Position;

public final class King extends Piece {

    private static final PieceType KING_TYPE = PieceType.KING;

    public King(Color color, Position position) {
        super(color, KING_TYPE, position);
    }
}
