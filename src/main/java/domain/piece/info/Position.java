package domain.piece.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int fileIndex() {
        return file.toIndex();
    }

    public int rankIndex() {
        return rank.toIndex();
    }

    public List<Position> findPathWithOutEndPoints(final Position target) {
        final Direction direction = Direction.between(this, target);
        final List<Position> path = new ArrayList<>();

        Position current = this;
        while (!current.equals(target)) {
            path.add(current);
            current = current.next(direction);
        }
        path.remove(this);
        path.remove(target);

        return path;
    }

    public Position next(final Direction direction) {
        return new Position(File.of(fileIndex() + direction.file()),
                Rank.of(rankIndex() + direction.rank()));
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
