package chess.domain.piece;

import chess.domain.board.MoveOrder;
import chess.domain.piece.movestrategy.MoveStrategy;

import java.util.Objects;

public abstract class Piece {

    private final Notation notation;
    private final Color color;
    private final MoveStrategy moveStrategy;

    protected Piece(final Notation notation, final Color color, final MoveStrategy moveStrategy) {
        this.notation = notation;
        this.color = color;
        this.moveStrategy = moveStrategy;

    }

    public final Notation getNotation() {
        return notation;
    }

    public final boolean isSameColor(final Color other) {
        return color == other;
    }

    public final double getScore() {
        return notation.getScore();
    }

    public final void checkMoveRange(final MoveOrder moverOrder) {
        if (!moveStrategy.canMove(moverOrder)) {
            throw new IllegalArgumentException("이동 불가한 위치입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return notation == piece.notation && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notation, color);
    }

    public final Color getColor() {
        return color;
    }
}
