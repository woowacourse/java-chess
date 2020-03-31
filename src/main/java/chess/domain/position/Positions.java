package chess.domain.position;

import java.util.HashSet;
import java.util.Set;

public class Positions {
    private final Set<Position> positions;

    public Positions(Set<Position> positions) {
        this.positions = positions;
    }

    private Positions(HashSet<Position> positions) {
        this.positions = positions;
    }

    public static Positions create() {
        return new Positions(new HashSet<>());
    }

    public void add(Position position) {
        this.positions.add(position);
    }

    public void addAll(Positions positions) {
        this.positions.addAll(positions.positions);
    }

    public boolean contains(Position position) {
        return positions.contains(position);
    }

    public Set<Position> getPositions() {
        return positions;
    }
}