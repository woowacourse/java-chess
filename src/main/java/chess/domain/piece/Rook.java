package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }
}
