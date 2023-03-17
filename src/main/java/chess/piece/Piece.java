package chess.piece;

import chess.Path;
import chess.Position;
import java.util.Optional;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public abstract Path searchPathTo(Position from, Position to, Optional<Piece> destination);

    protected void validateSameColor(Piece other) {
        if (color.isSameColor(other.color)) {
            throw new IllegalStateException("같은 색 말의 위치로 이동할 수 없습니다.");
        }
    }

    public boolean isSameColor(Color turn) {
        return this.color == turn;
    }
}
