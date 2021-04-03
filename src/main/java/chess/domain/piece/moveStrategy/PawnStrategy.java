package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PawnStrategy implements MoveStrategy {
    @Override
    public List<Position> searchMovablePositions(Position target, List<Direction> directions) {
        return directions.stream()
                .map(direction -> calculatePawnMovablePosition(target, direction))
                .collect(Collectors.toList())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Position> calculatePawnMovablePosition(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();

        int x = target.getHorizontalWeight() + direction.getX();
        int y = target.getVerticalWeight() + direction.getY();

        if (isInBorder(x, y)) {
            result.add(Position.of(Horizontal.findFromWeight(x), Vertical.findFromWeight(y))            );
        }

        return result;
    }

    private boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Direction direction = target.directionToDestination(destination);
        Piece targetPiece = board.findPieceFromPosition(target);
        Piece movedPositionPiece = board.findPieceFromPosition(target.moveTowardDirection(direction));

        if (isDiagonal(direction)) {
            return Objects.nonNull(movedPositionPiece) && movedPositionPiece.isDifferentTeam(targetPiece);
        }

        if (isTopBottom(direction)) {
            return check(target, destination, board);
        }
        return true;
    }

    private boolean check(Position target, Position destination, Board board) {
        Position movedPosition = target.moveTowardDirection(target.directionToDestination(destination));
        Piece movedPositionPiece = board.findPieceFromPosition(movedPosition);

        if (Objects.nonNull(movedPositionPiece)) {
            return false;
        }
        if (destination == movedPosition) {
            return true;
        }
        movedPosition = movedPosition.moveTowardDirection(target.directionToDestination(destination));
        movedPositionPiece = board.findPieceFromPosition(movedPosition);

        return Objects.isNull(movedPositionPiece);
    }

    private boolean isDiagonal(Direction direction) {
        return direction == Direction.RIGHT_TOP || direction == Direction.LEFT_TOP
                || direction == Direction.LEFT_BOTTOM || direction == Direction.RIGHT_BOTTOM;
    }

    private boolean isTopBottom(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }
}
