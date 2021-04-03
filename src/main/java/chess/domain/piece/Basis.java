package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.Objects;

public abstract class Basis implements Piece {
    private final String displayName;

    public Basis(final String displayName) {
        this.displayName = displayName;
    }

    public abstract void moveToEmpty(final Position to, final Pieces pieces);

    public abstract void moveForKill(final Position to, final Pieces pieces);

    public abstract Position getPosition();

    public abstract boolean isSameColor(final Color color);

    public abstract boolean isEmpty();

    public abstract boolean isKing();

    public abstract double score();

    public abstract boolean isPawn();

    public abstract Column getColumn();

    @Override
    public String display() {
        return displayName;
    }
}
