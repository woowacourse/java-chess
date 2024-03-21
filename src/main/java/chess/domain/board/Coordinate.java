package chess.domain.board;

import java.util.Objects;

public class Coordinate {

    private final Rank rank;
    private final File file;

    public Coordinate(int rankValue, char fileValue) {
        this.rank = new Rank(rankValue);
        this.file = new File(fileValue);
    }

    public char getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(rank, that.rank) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
