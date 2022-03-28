package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    private final PieceNotation notation;
    private final double score;

    protected Piece(final Color color, final PieceNotation notation, final double score) {
        this.color = color;
        this.notation = notation;
        this.score = score;
    }

    public final PieceNotation getNotation() {
        return notation;
    }

    public final boolean isSameColor(final Color other) {
        return color == other;
    }

    public final double getScore() {
        return score;
    }

    public final boolean isLinerMove(final Position from, final Position to) {
        final var fileDifference = Math.abs(from.getFileOrder() - to.getFileOrder());
        final var rankDifference = Math.abs(from.getRankNumber() - to.getRankNumber());

        return (fileDifference == 0 && rankDifference != 0) || (fileDifference != 0 && rankDifference == 0);
    }

    public final boolean isDiagonalMove(final Position from, final Position to) {
        final var fileDifference = Math.abs(from.getFileOrder() - to.getFileOrder());
        final var rankDifference = Math.abs(from.getRankNumber() - to.getRankNumber());

        return fileDifference == rankDifference;
    }

    public abstract void checkMoveRange(final Board board, final Position from, final Position to);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(notation, piece.notation) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notation, color);
    }
}
