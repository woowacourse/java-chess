package chess.domain.piece;

import chess.domain.Color;

public final class Bishop extends Piece{
    public Bishop(Color color) {
        super(color);
    }

    @Override
    boolean movable(String position, Piece target) {
        return false;
    }
}
