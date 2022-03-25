package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Queen extends Piece {
    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        if (!targetPiece.isAlly(this) && canMoveTo(targetPiece)) {
            return true;
        }
        return false;
    }

    @Override
    public String getLetter() {
        return "q";
    }

    private boolean canMoveTo(Piece target) {
        try {
            return direction().contains(this.findDirectionTo(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private List<Direction> direction() {
        return Direction.getNonKnightDirection();
    }
}
