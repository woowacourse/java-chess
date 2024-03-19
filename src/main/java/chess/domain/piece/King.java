package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class King extends Piece {
    public King(Color color) {
        super(color, PieceType.KING);
    }
}
