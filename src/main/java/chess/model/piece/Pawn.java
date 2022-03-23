package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;

public final class Pawn extends Piece {

    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
//        Square tempSquare = targetPiece;
//        if (firstLocation(targetPiece)) {
//            for (int i = 0; i < 2; i++) {
//                tempSquare = tempSquare.moveDirection(direction());
//                if (tempSquare.equals(targetSquare)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//        tempSquare = targetPiece.moveDirection(direction());
//        if (tempSquare.equals(targetSquare)) {
//            return true;
//        }
        return false;
    }

    private boolean firstLocation(Square square) {
        return square.isPawnFirstSquare(color());
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
