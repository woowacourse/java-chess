package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Pawn extends Piece {

    private static final String PAWN_NAME = "p";

    public Pawn(Color color, Square square) {
        super(color, square);
    }


    @Override
    public boolean movable(Piece targetPiece) {
        if (targetPiece.isEnemy(this)) {
            return canMoveToEnemy(targetPiece);
        }
        return movableToLinear(targetPiece);
    }

    private boolean movableToLinear(Piece targetPiece) {
        Direction directionTo = findDirectionTo(targetPiece);
        if (firstLocation()) {
            return direction().contains(directionTo) &&
                    moveOnceOrTwice(targetPiece, directionTo) &&
                    !isAlly(targetPiece);
        }
        return direction().contains(directionTo) &&
                targetPiece.isAt(square().tryToMove(directionTo)) &&
                !isAlly(targetPiece);
    }

    private boolean moveOnceOrTwice(Piece targetPiece, Direction directionTo) {
        return targetPiece.isAt(square().tryToMoveTwice(directionTo)) || targetPiece.isAt(
                square().tryToMove(directionTo));
    }

    private boolean canMoveToEnemy(Piece target) {
        try {
            return diagonalDirection().contains(findDirectionTo(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Point getPoint() {
        return Point.PAWN;
    }

    private boolean firstLocation() {
        return square().isPawnFirstSquare(color());
    }

    @Override
    public String getLetter() {
        return PAWN_NAME;
    }

    public List<Direction> direction() {
        if (this.isBlack()) {
            return List.of(Direction.SOUTH);
        }
        return List.of(Direction.NORTH);
    }

    private List<Direction> diagonalDirection() {
        if (this.isBlack()) {
            return List.of(Direction.SOUTHEAST, Direction.SOUTHWEST);
        }
        return List.of(Direction.NORTHEAST, Direction.NORTHWEST);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
