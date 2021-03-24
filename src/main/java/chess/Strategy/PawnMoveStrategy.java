package chess.Strategy;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PawnMoveStrategy implements MoveStrategy {
    private final Team team;

    public PawnMoveStrategy(Team team) {
        this.team = team;
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Direction direction = target.directionToDestination(destination);
        Piece targetPiece = board.findPieceFromPosition(target);
        Position movedPosition = target.moveTowardDirection(direction);
        Piece movedPositionPiece = board.findPieceFromPosition(movedPosition);

        if (direction.isDiagonal()) {
            return Objects.nonNull(movedPositionPiece) && movedPositionPiece.isDifferentTeam(targetPiece);
        }

        if (direction.isTopBottom()) {
            return checkWhenTopBottom(board, target, destination);
        }

        return false;
    }

    private boolean checkWhenTopBottom(Board board, Position target, Position destination) {
        Direction direction = target.directionToDestination(destination);
        Position movedPosition = target.moveTowardDirection(direction);
        Piece movedPositionPiece = board.findPieceFromPosition(movedPosition);

        if (Objects.nonNull(movedPositionPiece)) {
            return false;
        }
        if (destination.getVerticalWeight() - target.getVerticalWeight() == 2) {
            movedPosition = movedPosition.moveTowardDirection(direction);
            movedPositionPiece = board.findPieceFromPosition(movedPosition);
        }

        return Objects.isNull(movedPositionPiece);
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
        if (isInBorder(horizontalWeight, verticalWeight)) {
            result.add(Position.of(Horizontal.findFromWeight(horizontalWeight),
                    Vertical.findFromWeight(verticalWeight)));
        }
        return result;
    }

    private boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
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

    private boolean isBlack() {
        return team.isBlack();
    }

    private boolean isWhite() {
        return team.isWhite();
    }
}
