package chess.piece.directional;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends DirectionalPiece {

    public Pawn(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    protected List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        if (this.side == Side.WHITE) {
            directions.addAll(List.of(Direction.UP, Direction.UP_RIGHT, Direction.UP_LEFT));
        }
        if (this.side == Side.BLACK) {
            directions.addAll(List.of(Direction.DOWN, Direction.DOWN_RIGHT, Direction.DOWN_LEFT));
        }
        return directions;
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCount = position.getMoveCount(targetPosition, direction);

        if (isStartPosition() && moveCount == 2) {
            return direction == Direction.UP || direction == Direction.DOWN;
        }

        final boolean isPossibleDirection = directions.contains(direction);
        final boolean isPossibleDistance = moveCount == 1;

        return isPossibleDirection && isPossibleDistance;
    }

    private boolean isStartPosition() {
        final boolean isWhiteStartPosition = position.getRank() == 2 && side == Side.WHITE;
        final boolean isBlackStartPosition = position.getRank() == 7 && side == Side.BLACK;
        return isWhiteStartPosition || isBlackStartPosition;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Pawn(positionToMove, this.side);
    }

    @Override
    public boolean isNeedToCheckWhenDiagonalMove() {
        return true;
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCount = position.getMoveCount(targetPosition, direction);

        if (isStartPosition() && moveCount == 2) {
            return List.of(position.getNextPosition(direction));
        }
        return Collections.emptyList();
    }
}
