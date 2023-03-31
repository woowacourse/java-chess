package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public final List<Position> findPositions(final Position source, final Position target) {
        final List<Position> movablePositions = createMovablePositions(source, target);
        validateUnmovablePosition(movablePositions, target);

        return movablePositions;
    }

    private void validateUnmovablePosition(final List<Position> positions, final Position target) {
        if (!positions.contains(target)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
    }

    public final boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public final boolean isNotSameColor(final Color color) {
        return this.color != color;
    }

    public final boolean isSamePieceType(final PieceType type) {
        return this.pieceType == type;
    }

    public final boolean isNotSamePieceType(final PieceType type) {
        return this.pieceType != type;
    }

    public final PieceType getPieceType() {
        return pieceType;
    }

    public final Color getColor() {
        return color;
    }

    protected abstract List<Position> createMovablePositions(final Position source, final Position target);

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
