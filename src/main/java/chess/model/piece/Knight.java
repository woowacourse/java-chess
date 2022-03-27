package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Knight extends Piece{
    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        Direction directionTo = findDirectionTo(targetPiece);
        return direction().contains(directionTo) &&
                targetPiece.isAt(square().tryToMove(directionTo)) &&
                !targetPiece.isAlly(this);
    }

    @Override
    public String getLetter() {
        return "n";
    }

    @Override
    public Point getPoint() {
        return Point.KNIGHT;
    }

    private List<Direction> direction() {
        return Direction.getKnightDirection();
    }
}
