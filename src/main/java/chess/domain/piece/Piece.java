package chess.domain.piece;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    protected static <P extends Piece> Set<P> initialPiecesOf(
            final Set<Position> initialPositions,
            final Color color,
            final PieceConstructor<P> pieceConstructor
    ) {
        return initialPositions.stream()
                .map(position -> pieceConstructor.create(color, position))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Color color() {
        return color;
    }

    public Position position() {
        return position;
    }

    public abstract Set<Position> move(final Position source, final Position target);

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Piece other
                && color == other.color
                && position == other.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
