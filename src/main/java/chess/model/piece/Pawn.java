package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        Square tempSquare = square();
        for (Direction diagonalDirection : diagonalDirection()) {
            Square newSquare = tempSquare.tryToMove(diagonalDirection);
            if (targetPiece.isAt(newSquare) && isEnemy(targetPiece)) {
                return true;
            }
        }

        if (firstLocation()) {
            for (int i = 0; i < 2; i++) {
                tempSquare = tempSquare.tryToMove(direction());
                if (tempSquare.equals(targetPiece.square())) {
                    return true;
                }
            }
            return false;
        }
        tempSquare = square().tryToMove(direction());
        if (tempSquare.equals(targetPiece.square())) {
            return true;
        }
        return false;
    }

    private boolean firstLocation() {
        return square().isPawnFirstSquare(color());
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

    private List<Direction> diagonalDirection() {
        if (this.isBlack()) {
            return List.of(Direction.SOUTHEAST, Direction.SOUTHWEST);
        }
        return List.of(Direction.NORTHEAST, Direction.NORTHWEST);
    }
}
