package chess.domain.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Optional;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    final void validateSameColor(final Piece other) {
        if (color.isSameColor(other.color)) {
            throw new IllegalStateException("같은 색 말의 위치로 이동할 수 없습니다.");
        }
    }

    final public boolean isBlack() {
        return color == Color.BLACK;
    }

    final public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public abstract Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination);
}
