package chess.domain.piece;

import chess.domain.game.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Color color;
    protected final MovingStrategies movingStrategies;

    public Piece(final Color color, final PieceType pieceType, final MovingStrategies movingStrategies) {
        this.color = color;
        this.movingStrategies = movingStrategies;
        this.pieceType = pieceType;
    }

    public abstract List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Color targetColor);

    public final List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        final MovingStrategy movingStrategy = movingStrategies.findStrategy(source, target);
        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }
        return calculatePath(movingStrategy, source, target, targetColor);
    }

    public boolean isInitialPawn() {
        return false;
    }
    public final boolean isEmpty() {
        return color.isEmpty();
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public final Color getColor() {
        return color;
    }

    public final PieceType getPieceType() {
        return pieceType;
    }
}
