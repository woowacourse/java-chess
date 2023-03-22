package chess.domain;

import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public Position nextPosition(int rank, int file) {
        Rank nextRank = Rank.of(this.rank.getRank() + rank);
        File nextFile = File.ofByFile(this.file.getFile() + file);
        return new Position(nextRank, nextFile);
    }

    public int getRank() {
        return rank.getRank();
    }

    public int getFile() {
        return file.getFile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
