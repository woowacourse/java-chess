package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class King extends Piece {

    private static final PieceType KING_TYPE = PieceType.KING;

    private King(Color color) {
        super(color, KING_TYPE);
    }

    public static King create(Color color) {
        return new King(color);
    }
}
