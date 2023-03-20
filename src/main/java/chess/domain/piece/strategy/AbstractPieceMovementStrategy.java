package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public abstract class AbstractPieceMovementStrategy implements PieceMovementStrategy {

    protected final Color color;

    protected AbstractPieceMovementStrategy(final Color color) {
        this.color = color;
    }

    @Override
    public List<PiecePosition> waypoints(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        validateMove(path, nullableEnemy);
        return path.waypoints();
    }

    @Override
    public void validateMove(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        validateAllyKill(nullableEnemy);
        validateMoveWithNoAlly(path, nullableEnemy);
    }

    protected abstract void validateMoveWithNoAlly(final Path path, final Piece nullableEnemy) throws IllegalArgumentException;

    /**
     * @throws IllegalArgumentException 죽일 수 없는 경우
     */
    private void validateAllyKill(final Piece enemy) throws IllegalArgumentException {
        if (enemy == null) {
            return;
        }
        if (enemy.color() == color) {
            throw new IllegalArgumentException("아군이 있는 위치로는 이동할 수 없습니다.");
        }
    }

    @Override
    public Color color() {
        return this.color;
    }
}
