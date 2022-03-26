package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private final MovingStrategy movingStrategy;

    protected Piece(Color color, MovingStrategy movingStrategy) {
        this.color = color;
        this.movingStrategy = movingStrategy;
    }

    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        movingStrategy.validateMove(board, sourcePosition, targetPosition);
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isEmpty() {
        return false;
    }

    public abstract String getNotation();
}
