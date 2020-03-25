package chess.domain;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String coordinate) {
        String[] fileAndRank = coordinate.split("");
        return new Position(File.of(fileAndRank[0]), Rank.of(fileAndRank[1]));
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public boolean isNewRank() {
        return file == File.A;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public boolean isSameRank(Position target) {
        return this.rank == target.rank;
    }

    public boolean isSameFile(Position target) {
        return this.file == target.file;
    }

    public int calculateRankDistance(Position target) {
        return rank.getNumber() - target.rank.getNumber();
    }

    public int calculateFileDistance(Position target) {
        return file.getNumber() - target.file.getNumber();
    }
}
