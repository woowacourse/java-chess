package chess.domain.pieces.piece;

import chess.domain.score.Score;
import chess.domain.score.ScoreStatus;
import chess.domain.square.Movement;

import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(final Movement movement, final Piece target);

    public boolean isSameColor(final Piece piece) {
        if (piece == null) {
            return false;
        }
        return color.equals(piece.color);
    }

    public boolean isPawn() {
        return this.type.equals(Type.PAWN);
    }

    public boolean isKing() {
        return this.type.equals(Type.KING);
    }

    public Color color() {
        return color;
    }

    public Type type() {
        return type;
    }

    public Score getScore(ScoreStatus scoreStatus) {
        return scoreStatus.calculate(type.getScore());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color.equals(piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
