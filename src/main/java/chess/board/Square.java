package chess.board;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(String position) {
        this(File.find(position.substring(0, 1)), Rank.find(position.substring(1, 2)));
    }

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public String getName() {
        return file.getValue() + rank.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square)o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
