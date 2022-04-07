package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class FixedMovablePiece extends Piece {

    protected FixedMovablePiece(Color color, PieceType name) {
        super(color, name);
    }

    @Override
    public boolean canMove(Position fromPosition, Position toPosition) {
        Direction directionByPositions = Direction.getDirectionByPositions(fromPosition, toPosition);
        if (!directionByPositions.isInDirections(getMovableDirections())) {
            return false;
        }
        return directionByPositions.isSameWithDistanceAndDirection(fromPosition, toPosition);
    }
}
