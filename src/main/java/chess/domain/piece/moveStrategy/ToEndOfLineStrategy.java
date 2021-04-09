package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToEndOfLineStrategy extends MoveStrategy {
    @Override
    public List<Position> calculateBoardPosition(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int x = target.getHorizontalWeight();
        int y = target.getVerticalWeight();

        while (isInsideBoard(x, y, direction)) {
            x += direction.getX();
            y += direction.getY();
            result.add(Position.of(Horizontal.findFromWeight(x), Vertical.findFromWeight(y)));
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
