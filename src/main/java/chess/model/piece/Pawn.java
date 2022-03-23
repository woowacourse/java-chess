package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public String getLetter() {
        return "p";
    }


    public Direction direction() {
        if (this.isBlack()) {
            return Direction.SOUTH;
        }
        return Direction.NORTH;
    }
}
