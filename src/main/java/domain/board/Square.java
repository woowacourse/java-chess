package domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        SquareName squareName = new SquareName(String.valueOf(new char[]{file.getValue(), rank.getValue()}));
        if (SquareCache.contains(squareName)) {
            return SquareCache.getSquare(squareName);
        }
        Square square = new Square(file, rank);
        SquareCache.putSquare(squareName, square);
        return square;
    }

    public static Square of(int fileCoordinate, int rankCoordinate) {
        File file = File.findFile(fileCoordinate);
        Rank rank = Rank.findRank(rankCoordinate);
        return of(file, rank);
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
        Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
