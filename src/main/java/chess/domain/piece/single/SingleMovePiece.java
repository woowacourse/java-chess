package chess.domain.piece.single;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.PieceRule;
import java.util.List;

public abstract class SingleMovePiece implements PieceRule {

    private static final int MOVABLE_COUNT = 1;

    private final List<Direction> moveDirections;

    protected SingleMovePiece(List<Direction> moveDirections) {
        this.moveDirections = moveDirections;
    }

    @Override
    public final PieceRule move(Position source, Position target, ChessBoard chessBoard) {
        if (isMovable(source, target)) {
            return this;
        }
        throw new IllegalStateException("움직일 수 없는 곳입니다.");
    }

    private boolean isMovable(Position source, Position target) {
        return moveDirections.stream()
                .map(direction -> direction.route(source, target))
                .anyMatch(route -> route.size() == MOVABLE_COUNT);
    }
}
