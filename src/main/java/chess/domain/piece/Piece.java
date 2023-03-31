package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Set;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract Set<Position> computePath(final Position source, final Position target);

    public abstract boolean canMove(
            final Map<Position, Boolean> isEmptyPosition,
            final Position source,
            final Position target
    );

    public abstract Kind getKind();

    public abstract boolean isEmpty();

    public boolean equalsColor(final Piece targetPiece) {
        return color == targetPiece.color;
    }

    public boolean equalsColor(final Color color) {
        return this.color == color;
    }

    public boolean differsColor(final Color color) {
        return this.color != color;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return getKind().getScore();
    }

    public String getName() {
        return getKind().name();
    }
}
