package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class StraightMovablePiece extends Piece {

    protected StraightMovablePiece(Color color, PieceType name) {
        super(color, name);
    }

    @Override
    public boolean canMove(Position fromPosition, Position toPosition) {
        Direction directionByPositions = Direction.getDirectionByPositions(fromPosition, toPosition);
        return directionByPositions.isInDirections(getMovableDirections());
    }
}
