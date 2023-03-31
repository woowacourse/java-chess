package chess.domain.piece.normal;

import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Normal extends Piece {

    public Normal(final Color color) {
        super(color);
    }

    public abstract Set<Position> computePath(final Position source, final Position target);

    public boolean canMove(
            final Map<Position, Boolean> isEmptyPosition,
            final Position source,
            final Position target) {
        final var copied = new HashMap<>(isEmptyPosition);
        copied.remove(target);
        return copied.keySet()
                .stream()
                .allMatch(copied::get);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
