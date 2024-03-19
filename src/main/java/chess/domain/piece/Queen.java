package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }
}
