package chess.domain.piece;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

public abstract class Piece {

    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public abstract Path searchPathTo(Position from, Position to, Piece locatedPiece);

    protected void validateSameColor(Piece other) {
        if (other != null && color.isSameColor(other.color)) {
            throw new IllegalStateException("같은 색 말의 위치로 이동할 수 없습니다.");
        }
    }

    public boolean isSameColor(Color color) {
        return this.color.isSameColor(color);
    }

    public boolean isDifferentColor(Color color) {
        return this.color.isDifferentColor(color);
    }
}
