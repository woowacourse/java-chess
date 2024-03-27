package chess.domain.piece.slidingpiece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Set;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(Color color) {
        super(color);
    }

    abstract Set<Direction> directions();

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        return isValidDirection(source, target)
            && isNoPieceOnRoute(source, target, board)
            && (board.get(target).doesNotExist() || board.get(target).hasOppositeColorFrom(this));
    }

    @Override
    public boolean exists() {
        return true;
    }

    private boolean isValidDirection(Position source, Position target) {
        if (isSlidingMove(source, target)) {
            return directions().contains(Direction.of(source, target));
        }
        return false;
    }

    private boolean isSlidingMove(Position source, Position target) {
        return source.isOnSameDiagonalAs(target)
            || source.isOnSameRankAs(target)
            || source.isOnSameFileAs(target);
    }

    private boolean isNoPieceOnRoute(Position source, Position target, Map<Position, Piece> board) {
        Direction direction = Direction.of(source, target);
        Position current = source.nextPosition(direction);
        while (!current.equals(target) && board.get(current).doesNotExist()) {
            current = current.nextPosition(direction);
        }
        return current.equals(target);
    }
}
