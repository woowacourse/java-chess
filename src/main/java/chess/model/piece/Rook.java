package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Rook extends Piece {

    public Rook(Color color, Square square) {
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
    public Point getPoint() {
        return Point.ROOK;
    }

    private boolean canMoveTo(Piece target) {
        try {
            return direction().contains(this.findDirectionTo(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getLetter() {
        return "r";
    }

    private List<Direction> direction() {
        return List.of(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST);
    }
}
