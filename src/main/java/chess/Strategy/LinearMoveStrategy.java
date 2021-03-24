package chess.Strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

import java.util.Objects;

public abstract class LinearMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Position movedPosition = calculateFarthestPosition(target, destination, board);
        if (movedPosition != destination) {
            return false;
        }
        return isPositionEmptyOrOppositeTeamPiece(target, movedPosition, board);
    }

    private Position calculateFarthestPosition(Position target, Position destination, Board board) {
        Position movedPosition = target;
        Direction direction = target.directionToDestination(destination);
        boolean moveContinueFlag = true;
        while (moveContinueFlag) {
            movedPosition = movedPosition.moveTowardDirection(direction);
            moveContinueFlag = isBlockedBeforeDestination(movedPosition, destination, board);
        }
        return movedPosition;
    }

    private boolean isBlockedBeforeDestination(Position movedPosition, Position destination, Board board) {
        return (movedPosition != destination) && (Objects.isNull(board.findPieceFromPosition(movedPosition)));
    }

    private boolean isPositionEmptyOrOppositeTeamPiece(Position target, Position movedPosition, Board board) {
        Piece targetPiece = board.findPieceFromPosition(target);
        Piece destinationPiece = board.findPieceFromPosition(movedPosition);
        return destinationPiece == null || !destinationPiece.isSameTeam(targetPiece);
    }

    public boolean isInBorder(int horizontalWeight, int verticalWeight, Direction direction) {
        return horizontalWeight + direction.getX() >= Board.MIN_BORDER
                && horizontalWeight + direction.getX() <= Board.MAX_BORDER
                && verticalWeight + direction.getY() >= Board.MIN_BORDER
                && verticalWeight + direction.getY() <= Board.MAX_BORDER;
    }
}
