package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
