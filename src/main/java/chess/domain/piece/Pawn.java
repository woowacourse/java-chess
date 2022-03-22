package chess.domain.piece;

import chess.domain.Color;

public final class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
    }

    @Override
    boolean movable(String position, Piece target) {
        return false;
    }
}
