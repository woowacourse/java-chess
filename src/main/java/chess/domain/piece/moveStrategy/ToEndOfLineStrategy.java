package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

import java.util.Objects;

public class ToEndOfLineStrategy implements CanMoveStrategy {
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
