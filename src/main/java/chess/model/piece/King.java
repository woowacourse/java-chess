package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class King extends Piece {
    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        for (Direction direction : direction()) {
            Square tempSquare = square();
            tempSquare = tempSquare.tryToMove(direction);
            if (tempSquare.equals(targetPiece.square()) && !this.isAlly(targetPiece)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getLetter() {
        return "k";
    }

    private List<Direction> direction() {
        return List.of(Direction.values());
    }
}
