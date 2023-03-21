package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.Strategy;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    protected final Strategy strategy;

    protected Piece(final Color color, final Strategy strategy) {
        this.color = color;
        this.strategy = strategy;
    }

    public List<Square> findRoute(final Square source, final Square destination) {
        return strategy.findRoute(source, destination);
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

    public abstract boolean isMovable(final Square source, final List<Square> route, final BoardSnapShot boardSnapShot);
}
