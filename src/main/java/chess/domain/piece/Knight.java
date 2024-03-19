package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }
}
