package chess.piece;

import chess.Color;
import chess.Position;

import java.util.Map;

public class BlankPiece extends Piece {

    public BlankPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces) {
        return false;
    }

    @Override
    public String toString() {
        return "_";
    }
}
