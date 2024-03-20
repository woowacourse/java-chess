package domain;

import java.util.Objects;

public class Square {

    private final Rank rank;
    private final File file;

    public Square(final Rank rank, final File file) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(final String input) {
        final String file = String.valueOf(input.charAt(0));
        final String rank = String.valueOf(input.charAt(1));

        return new Square(Rank.from(rank), File.from(file));
    }

    public Square next(final int rankDirection, final int fileDirection) {
        return new Square(rank.move(rankDirection), file.move(fileDirection));
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Square square)) {
            return false;
        }
        return getFile() == square.getFile() && getRank() == square.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFile(), getRank());
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
