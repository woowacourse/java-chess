package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }
}
