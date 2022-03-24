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
        for (Direction direction : direction()) {
            Square tempSquare = square().tryToMove(direction);
            if (tempSquare.equals(targetPiece.square()) && !this.isAlly(targetPiece)) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public String getLetter() {
        return "n";
    }

    private List<Direction> direction() {
        return Direction.getKnightDirection();
    }
}
