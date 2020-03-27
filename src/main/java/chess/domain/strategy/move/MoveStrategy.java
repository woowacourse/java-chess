package chess.domain.strategy.move;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.strategy.move.direction.Direction;

import java.util.List;

public abstract class MoveStrategy {

    protected boolean isSamePosition(Position source, Position target) {
        return source.equals(target);
    }

    protected boolean checkObstacle(Position source, Position target, Board board) {
        Direction direction = Direction.findDirection(source, target);
        List<Position> path = direction.findPath(source, target);

        return path.stream()
                .allMatch(board::isEmpty);
    }

    protected boolean checkTarget(Position source, Position target, Board board) {
        Piece sourcePiece = board.getPiece(source);
        if (board.isEmpty(target)) {
            return true;
        }
        Piece targetPiece = board.getPiece(target);
        return sourcePiece.isEnemy(targetPiece);
    }

    public abstract boolean movable(Position source, Position target, Board board);
}
