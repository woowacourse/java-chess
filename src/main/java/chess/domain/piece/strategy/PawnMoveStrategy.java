package chess.domain.piece.strategy;


import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class PawnMoveStrategy implements MoveStrategy {

    private static final int LINEAR_DIRECTION_DEGREE = 0;

    @Override
    public boolean movable(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition,
        Piece sourcePiece
    ) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        if (direction.getXDegree() == LINEAR_DIRECTION_DEGREE) {
            return linearPawnMove(chessBoard, sourcePosition, targetPosition, sourcePiece,
                direction);
        }
        Position nextPosition = sourcePosition.nextPosition(direction);
        return !chessBoard.isBlank(nextPosition)
            && !sourcePiece.isSameColor(chessBoard.getPiece(nextPosition))
            && nextPosition.equals(targetPosition);
    }

    private static boolean linearPawnMove(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition,
        Piece sourcePiece,
        Direction direction
    ) {
        Position nextPosition = sourcePosition.nextPosition(direction);
        if (!chessBoard.isBlank(nextPosition)) {
            return false;
        }
        if (nextPosition.equals(targetPosition)) {
            return true;
        }
        if (sourcePosition.isStartingPosition(sourcePiece.getColor())) {
            nextPosition = nextPosition.nextPosition(direction);
            return chessBoard.isBlank(nextPosition) && nextPosition.equals(targetPosition);
        }
        return false;
    }
}
