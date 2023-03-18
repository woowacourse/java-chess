package chess.piece;

import chess.path.Movement;
import chess.path.Path;
import chess.position.Position;
import java.util.List;
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

    protected void validateMovement(final Movement movement, List<Movement> availableMovements) {
        if (!availableMovements.contains(movement)) {
            throw new IllegalStateException("움직일 수 없는 방향임!");
        }
    }

    protected final void validateSameColor(Piece other) {
        if (color.isSameColor(other.color)) {
            throw new IllegalStateException("같은 색 말의 위치로 이동할 수 없습니다.");
        }
    }

    public final boolean isSameColor(Color turn) {
        return this.color.equals(turn);
    }
}
