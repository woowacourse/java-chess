package chess.piece.special;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends SpecialPiece {

    private final List<Direction> directions;

    public Pawn(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        if (this.side == Side.WHITE) {
            initWhiteSideDirections(directions);
        }
        if (this.side == Side.BLACK) {
            initBlackSideDirections(directions);
        }
        return directions;
    }

    private void initWhiteSideDirections(final List<Direction> directions) {
        directions.add(Direction.UP);
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.UP_LEFT);
    }

    private void initBlackSideDirections(final List<Direction> directions) {
        directions.add(Direction.DOWN);
        directions.add(Direction.DOWN_RIGHT);
        directions.add(Direction.DOWN_LEFT);
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
    public boolean isPawn() {
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
