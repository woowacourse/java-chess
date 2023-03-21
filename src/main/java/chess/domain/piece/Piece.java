package chess.domain.piece;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    private final PieceType pieceType;

    public Piece(final Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public abstract Path searchPathTo(Position from, Position to, Piece destination);

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
