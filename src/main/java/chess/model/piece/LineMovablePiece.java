package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public abstract class LineMovablePiece extends Piece {

    public LineMovablePiece(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        return !isAlly(targetPiece) && canMoveTo(targetPiece);
    }

    private boolean canMoveTo(Piece target) {
        try {
            return direction().contains(findDirectionTo(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    abstract List<Direction> direction();

}
