package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class King extends Piece {

    private static final PieceType KING_TYPE = PieceType.KING;

    public King(Color color) {
        super(color, KING_TYPE);
    }
}
