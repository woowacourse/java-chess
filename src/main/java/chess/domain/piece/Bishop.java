package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }
}
