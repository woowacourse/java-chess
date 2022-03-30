package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.notation.Color;
import chess.domain.piece.notation.ColorNotation;
import chess.domain.piece.notation.PieceNotation;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final ColorNotation notation;
    protected final List<Direction> directions;

    protected Piece(final ColorNotation notation, final List<Direction> directions) {
        this.notation = notation;
        this.directions = directions;
    }

    public final PieceNotation getNotation() {
        return notation.getPieceNotation();
    }

    public final boolean isSameColor(final Color other) {
        return notation.isSameColor(other);
    }

    public final double getScore() {
        return notation.getScore();
    }

    public void checkMoveRange(final Board board, final Position from, final Position to) {
        final var direction = Direction.of(from, to);
        if (directions.contains(direction)) {
            board.checkHasPiece(direction.getPositions(from, to));
            return;
        }
        throw new IllegalArgumentException("이동 불가한 위치입니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(notation, piece.notation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notation);
    }
}
