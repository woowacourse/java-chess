package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isSameRank(final Square targetSquare) {
        return this.rank == targetSquare.rank;
    }

    public boolean isSameFile(final Square targetSquare) {
        return this.file == targetSquare.file;
    }

    public int calculateRankDistance(final Square targetSquare) {
        return this.rank.calculateDistance(targetSquare.rank);
    }

    public int calculateFileDistance(final Square targetSquare) {
        return this.file.calculateDistance(targetSquare.file);
    }

    public int calculateRankDifference(final Square targetSquare) {
        return this.rank.calculateDifference(targetSquare.rank);
    }

    public List<Square> getSquaresInSameRank(final Square square) {
        return file.getFilesInRange(square.file)
                .stream()
                .map(file -> new Square(file, rank))
                .collect(Collectors.toList());
    }

    public List<Square> getSquaresInSameFile(final Square square) {
        return rank.getRanksInRange(square.rank)
                .stream()
                .map(rank -> new Square(file, rank))
                .collect(Collectors.toList());
    }

    public List<Square> getDiagonalSquares(final Square square) {
        final List<Square> squares = new ArrayList<>();
        final List<File> files = file.getFilesInRange(square.file);
        final List<Rank> ranks = rank.getRanksInRange(square.rank);

        for (int i = 0, end = files.size(); i < end; i++) {
            squares.add(new Square(files.get(i), ranks.get(i)));
        }
        return squares;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Square square = (Square) o;
        return Objects.equals(file, square.file) && Objects.equals(rank, square.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public boolean isRankSeven() {
        return rank == Rank.SEVEN;
    }

    public boolean isRankTwo() {
        return rank == Rank.TWO;
    }

    static public List<List<Square>> getEachFileSquares() {
        return Arrays.stream(File.values())
                .map(file -> getRankSquares(file))
                .collect(Collectors.toList());
    }

    static private List<Square> getRankSquares(final File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Square(file, rank))
                .collect(Collectors.toList());
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
