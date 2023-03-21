package domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Square(int fileCoordinate, int rankCoordinate) {
        this.file = File.find(fileCoordinate);
        this.rank = Rank.find(rankCoordinate);
    }

    public List<Integer> toCoordinate() {
        ArrayList<Integer> coordinate = new ArrayList<>();
        coordinate.add(file.ordinal());
        coordinate.add(rank.ordinal());
        return coordinate;
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
