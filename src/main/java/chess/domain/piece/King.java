package chess.domain.piece;

import chess.domain.Color;

public final class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    boolean movable(String position, Piece target) {
        return false;
    }
}
