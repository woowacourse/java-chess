package chess.domain.piece;

import chess.domain.path.Movement;
import chess.domain.path.Path;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    private final PieceType type;

    public Piece(final Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract Path searchPathTo(Position from, Position to, Piece destination);

    public abstract double calculateScore(boolean hasOtherPieceInSameFile);

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

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public Color color() {
        return color;
    }

    public boolean isTypeOf(PieceType pieceType) {
        return this.type == pieceType;
    }
}
