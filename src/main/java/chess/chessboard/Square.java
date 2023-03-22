package chess.chessboard;

import java.util.*;
import java.util.stream.Collectors;

public class Square {

    private static final Map<Integer, Square> cache = new HashMap<>();

    private final Rank rank;
    private final File file;

    private Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(final Rank rank, final File file) {
        return cache.computeIfAbsent(toKey(rank, file), key -> new Square(rank, file));
    }

    private static Integer toKey(final Rank rank, final File file) {
        return Objects.hash(rank, file);
    }

    public boolean isBackOf(final Square other, final Side side) {
        if (side == Side.WHITE) {
            return other.rank.isBiggerThan(this.rank);
        }
        return this.rank.isBiggerThan(other.rank);
    }

    public List<Square> squaresOfPath(Square destination) {
        if (isLine(destination)) {
            return squaresOfLine(destination);
        }
        if (isDiagonal(destination)) {
            return squaresOfDiagonal(destination);
        }
        return Collections.emptyList();
    }

    private boolean isLine(final Square other) {
        validateNotSameSquare(other);

        return this.rank == other.rank || this.file == other.file;
    }

    private boolean isDiagonal(final Square other) {
        validateNotSameSquare(other);

        final int verticalDistance = this.rank.distanceTo(other.rank);
        final int horizontalDistance = this.file.distanceTo(other.file);

        return verticalDistance == horizontalDistance;
    }

    public void validateNotSameSquare(final Square other) {
        if (this == other) {
            throw new IllegalArgumentException("같은 위치의 square입니다");
        }
    }

    private List<Square> squaresOfLine(final Square otherSquare) {
        assert isLine(otherSquare);

        if (otherSquare.rank == this.rank) {
            return squaresOfRank(otherSquare);
        }
        return squaresOfFile(otherSquare);
    }

    private List<Square> squaresOfRank(final Square other) {
        return File.filesBetween(this.file, other.file)
                   .stream()
                   .map(foundFile -> Square.of(rank, foundFile))
                   .collect(Collectors.toUnmodifiableList());
    }

    private List<Square> squaresOfFile(final Square otherSquare) {
        return Rank.ranksBetween(this.rank, otherSquare.rank)
                   .stream()
                   .map(foundRank -> Square.of(foundRank, file))
                   .collect(Collectors.toUnmodifiableList());
    }

    private List<Square> squaresOfDiagonal(final Square otherSquare) {
        assert isDiagonal(otherSquare);

        final List<Rank> ranks = Rank.ranksBetween(this.rank, otherSquare.rank);
        final List<File> files = File.filesBetween(this.file, otherSquare.file);
        final List<Square> squares = new ArrayList<>();

        for (int i = 0; i < ranks.size(); i++) {
            final Square nextSquare = Square.of(ranks.get(i), files.get(i));
            squares.add(nextSquare);
        }

        return squares;
    }

    public int calculateVerticalDistance(final Square other) {
        return rank.distanceTo(other.rank);
    }

    public int calculateHorizontalDistance(final Square other) {
        return file.distanceTo(other.file);
    }

    public boolean isAtRank(final Rank rank) {
        return this.rank == rank;
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
