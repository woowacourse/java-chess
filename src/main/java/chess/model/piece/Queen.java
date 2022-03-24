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
        Square nowSquare;
        for (Direction direction : direction()) {
            Square tempSquare = square();
            do {
                nowSquare = tempSquare;
                tempSquare = nowSquare.tryToMove(direction);
                if (tempSquare.equals(targetPiece.square()) && !this.isAlly(targetPiece)) {
                    return true;
                }
            } while (!tempSquare.equals(nowSquare));
        }
        return false;
    }

    @Override
    public String getLetter() {
        return "q";
    }

    private List<Direction> direction() {
        return Direction.getNonKnightDirection();
    }
}
