package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

import java.util.Objects;

public class PawnStrategy extends MoveStrategy {

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
