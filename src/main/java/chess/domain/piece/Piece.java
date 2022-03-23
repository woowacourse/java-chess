package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public final boolean isSameType(Class<? extends Piece> type) {
        return this.getClass() == type;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    public final boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public abstract boolean canMove(Position src, Position dest);

    public abstract Direction findDirection(Position src, Position dest);
}
