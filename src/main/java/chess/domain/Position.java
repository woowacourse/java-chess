package chess.domain;

import java.util.Objects;

public final class Position {

    private final Rank rank;
    private final File file;

     private Position(final int rank, final char file) {
        this.rank = new Rank(rank);
        this.file = new File(file);
    }

    private Position(final int rank, final File file) {
        this.rank = new Rank(rank);
        this.file = file;
    }

    public static Position from(final String input) {
        return from(Integer.parseInt(String.valueOf(input.charAt(1))) - 1, input.charAt(0));
    }

    public static Position from(final int rank, final char file) {
        return new Position(rank, file);
    }

    public Position changePosition(int rank) {
        return new Position(rank, this.file);
    }

    public int getRankValue() {
        return this.rank.getRank();
    }

    public char getFileValue() {
        return this.file.getFile();
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(rank, position.rank) && Objects.equals(file, position.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public int calculateFileDistance(final Position position) {
        return this.file.calculateDistance(position.file.getFile());
    }

    public int calculateRankDistance(final Position position) {
        return this.rank.calculateDistance(position.rank.getRank());
    }

    public Position move(int fileDirection, int rankDirection) {
        return new Position(rank.move(rankDirection), file.move(fileDirection));
    }

}
