package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Bishop extends Piece{
    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        if (!targetPiece.isAlly(this) && canMoveTo(targetPiece)) {
            return true;
        }
        return false;
    }

    private boolean canMoveTo(Piece target) {
        try {
            return direction().contains(this.findDirection(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getLetter() {
        return "b";
    }

    private List<Direction> direction() {
        return List.of(Direction.SOUTHEAST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}
