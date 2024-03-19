package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }
}
