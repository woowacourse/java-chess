package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";
    private static final double SCORE = 1;

    public Pawn(Team team) {
        super(PAWN_NAME, team, SCORE);
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Direction direction = target.directionToDestination(destination);
        Piece targetPiece = board.findPieceFromPosition(target);

        Position movedPosition = target.moveTowardDirection(direction);
        Piece movedPositionPiece = board.findPieceFromPosition(movedPosition);

        if (isTopBottom(direction)) {
            if (Objects.nonNull(movedPositionPiece)) {
                return false;
            }
            if (destination.getVerticalWeight() - target.getVerticalWeight() == 2) {
                movedPosition = movedPosition.moveTowardDirection(direction);
                movedPositionPiece = board.findPieceFromPosition(movedPosition);
                if (Objects.nonNull(movedPositionPiece)) {
                    return false;
                }
            }
        }

        if (isDiagonal(direction)) {
            return !Objects.isNull(movedPositionPiece) && !movedPositionPiece.isSameTeam(targetPiece);
        }
        return true;
    }

    private boolean isDiagonal(Direction direction) {
        return direction == Direction.RIGHT_TOP || direction == Direction.LEFT_TOP
                || direction == Direction.LEFT_BOTTOM || direction == Direction.RIGHT_BOTTOM;
    }

    private boolean isTopBottom(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();
        Direction direction = findDirection();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        if (isFirstStep(target)) {
            movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight, verticalWeight + direction.getY() * 2));
        }
        movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight, verticalWeight + direction.getY()));

        movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight - 1, verticalWeight + direction.getY()));
        movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight + 1, verticalWeight + direction.getY()));

        return movablePositions;
    }

    private List<Position> calculatePawnMovablePosition(int horizontalWeight, int verticalWeight) {
        List<Position> result = new ArrayList<>();
        if (horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER) {
            result.add(Position.of(Horizontal.findFromWeight(horizontalWeight),
                    Vertical.findFromWeight(verticalWeight)));
        }
        return result;
    }

    private Direction findDirection() {
        if (isBlack()) {
            return Direction.BOTTOM;
        }
        return Direction.TOP;
    }

    private boolean isFirstStep(Position position) {
        if (isBlack() && position.isSameVertical(Vertical.SEVEN)) {
            return true;
        }
        return isWhite() && position.isSameVertical(Vertical.TWO);
    }
}
