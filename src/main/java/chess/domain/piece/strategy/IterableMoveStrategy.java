package chess.domain.piece.strategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class IterableMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition,
        Piece sourcePiece
    ) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        Position nextPosition = sourcePosition.nextPosition(direction);
        while (!nextPosition.equals(targetPosition)) {
            if (!chessBoard.isBlank(nextPosition)) {
                break;
            }
            nextPosition = nextPosition.nextPosition(direction);
        }
        return !sourcePiece.isSameColor(chessBoard.getPiece(nextPosition));
    }
}
