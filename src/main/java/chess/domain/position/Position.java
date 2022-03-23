package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;
    
    private static final List<Position> TOTAL = new ArrayList<>();

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        Position newPosition = new Position(file, rank);
        return TOTAL.stream()
                .filter(position -> position.equals(newPosition))
                .findFirst()
                .orElse(newPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (file != position.file) return false;
        return rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
