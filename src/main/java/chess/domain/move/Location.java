package chess.domain.move;

import chess.domain.piece.Position;

import java.util.HashSet;
import java.util.Set;

public class Location {
    private final Set<Position> positions;

    public Location() {
        this.positions = new HashSet<>();
    }

    public void add(final Set<Position> positions) {
        this.positions.addAll(positions);
    }

    public boolean contains(final Position target) {
        return positions.contains(target);
    }
}
