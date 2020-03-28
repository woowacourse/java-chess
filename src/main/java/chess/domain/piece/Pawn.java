package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

public abstract class Pawn extends Piece {
    public Pawn(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    protected boolean isInBoardRange(Position nextPosition) {
        return nextPosition.getX() <= 8 && nextPosition.getX() >= 1 &&
                nextPosition.getY() <= 8 && nextPosition.getY() >= 1;
    }

    protected boolean isFirstMove(int initialPawnRow) {
        return position.getY() == initialPawnRow;
    }

    protected boolean isForwardDirection(Direction direction, Direction forwardDirection) {
        return direction == forwardDirection;
    }
}
