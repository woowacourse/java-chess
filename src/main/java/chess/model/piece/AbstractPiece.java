package chess.model.piece;

import chess.model.Board;
import chess.model.square.Square;

public abstract class AbstractPiece implements Piece {

    protected final Color color;

    protected AbstractPiece(Color color) {
        this.color = color;
    }

    @Override
    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    @Override
    public boolean movable(Board board, Square source, Square target) {
        return movable(source, target);
    }

    @Override
    public boolean isNotAlly(Piece target) {
        return this.color != target.color();
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    @Override
    public Color color() {
        return color;
    }
}
