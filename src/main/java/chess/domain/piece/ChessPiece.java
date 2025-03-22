package chess.domain.piece;

import chess.domain.position.Position;
import chess.dto.PromotionOrder;

import java.util.List;

public abstract class ChessPiece {

    protected final Color color;

    protected Position position;
    protected int moveCount = 0;

    public ChessPiece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(final Position newPosition) {
        moveCount++;
        if (!calculateCanMovePositions().contains(newPosition)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
        this.position = newPosition;
    }

    public void take(final Position newPosition) {
        moveCount++;
        if (!canTake(newPosition)) {
            throw new IllegalArgumentException("잡을 수 없는 위치입니다.");
        }
        this.position = newPosition;
    }

    public boolean canTake(final Position newPosition) {
        return calculateCanTakePositions().contains(newPosition);
    }

    public Position getPosition() {
        return this.position;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract boolean isCheckmatedBy(final List<Position> allyPiecePositions, final List<ChessPiece> enemyPieces);

    public abstract void castling(final ChessPiece other);

    public abstract ChessPiece promotion(PromotionOrder promotionOrder);

    public abstract boolean isPromotionable();

    protected abstract List<Position> calculateCanMovePositions();

    protected abstract List<Position> calculateCanTakePositions();
}
