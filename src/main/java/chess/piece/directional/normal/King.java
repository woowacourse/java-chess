package chess.piece.directional.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

public class King extends NormalPiece {

    public King(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    protected List<Direction> initDirections() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final boolean isPossibleDirection = directions.contains(direction);
        final boolean isPossibleDistance = position.getMoveCount(targetPosition, direction) == 1;
        return isPossibleDistance && isPossibleDirection;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new King(positionToMove, this.side);
    }
}
