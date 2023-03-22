package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.Strategy;

public abstract class Piece {

    protected final Color color;
    protected final Strategy strategy;
    protected final double score;

    protected Piece(final Color color, final Strategy strategy, final double score) {
        this.color = color;
        this.strategy = strategy;
        this.score = score;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean hasSameColor(final Piece piece) {
        return isSameColor(piece.color);
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }

    public abstract boolean isMovable(final Square source, final Square destination, final BoardSnapShot boardSnapShot);

    public abstract boolean isPawn();
}
