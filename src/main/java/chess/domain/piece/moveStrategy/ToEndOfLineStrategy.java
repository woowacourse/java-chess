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

public class ToEndOfLineStrategy implements MoveStrategy {
    public List<Position> searchMovablePositions(Position target, List<Direction> directions) {
        return directions.stream()
                .map(direction -> calculateBishopMovablePositions(target, direction))
                .collect(Collectors.toList())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Position> calculateBishopMovablePositions(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();
        while (isInsideBoard(horizontalWeight, verticalWeight, direction)) {
            horizontalWeight += direction.getX();
            verticalWeight += direction.getY();
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }

    private boolean isInsideBoard(int horizontalWeight, int verticalWeight, Direction direction) {
        return horizontalWeight + direction.getX() >= Board.MIN_BORDER
                && horizontalWeight + direction.getX() <= Board.MAX_BORDER
                && verticalWeight + direction.getY() >= Board.MIN_BORDER
                && verticalWeight + direction.getY() <= Board.MAX_BORDER;
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Position movedPosition = findPath(target, destination, board);

        if (checkBlocked(movedPosition, destination, board)) {
            return false;
        }
        Piece targetPiece = board.findPieceFromPosition(target);
        Piece destinationPiece = board.findPieceFromPosition(movedPosition);
        return Objects.isNull(destinationPiece) || destinationPiece.isDifferentTeam(targetPiece);
    }

    private Position findPath(Position target, Position destination, Board board) {
        Direction direction = target.directionToDestination(destination);
        Position movedPosition = target;
        boolean loop = true;

        while (loop) {
            movedPosition = movedPosition.moveTowardDirection(direction);
            loop = checkPath(movedPosition, destination, board) && isNotArrived(movedPosition, destination);
        }

        return movedPosition;
    }

    private boolean checkPath(Position movedPosition, Position destination, Board board) {
        return movedPosition == destination || Objects.isNull(board.findPieceFromPosition(movedPosition));
    }

    private boolean checkBlocked(Position movedPosition, Position destination, Board board) {
        return movedPosition != destination && Objects.nonNull(board.findPieceFromPosition(movedPosition));
    }

    private boolean isNotArrived(Position movedPosition, Position destination) {
        return movedPosition != destination;
    }
}
