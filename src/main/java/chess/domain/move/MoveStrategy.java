package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.move.direction.DirectionStrategy;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;

public abstract class MoveStrategy {

    protected boolean isSamePosition(Position source, Position target) {
        return source.equals(target);
    }

    protected boolean checkObstacle(Position source, Position target, Board board) {
        DirectionStrategy directionStrategy = DirectionStrategyFactory.find(source, target);
        List<Position> path = directionStrategy.find(source, target);

        for (Position position : path) {
            if (!board.isEmpty(position)) {
                return false;
            }
        }
        return true;
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
