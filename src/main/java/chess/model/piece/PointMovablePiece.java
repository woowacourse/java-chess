package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public abstract class PointMovablePiece extends Piece {

    public PointMovablePiece(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        Direction directionTo = findDirectionTo(targetPiece);
        return direction().contains(directionTo) &&
                targetPiece.isAt(square().tryToMove(directionTo)) &&
                !isAlly(targetPiece);
    }

    abstract List<Direction> direction();
}
