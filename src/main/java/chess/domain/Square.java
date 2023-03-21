package chess.domain;

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

    public void validateNotSameSquare(final Square other) {
        if (this == other) {
            throw new IllegalArgumentException("같은 위치의 square입니다");
        }
    }

    public boolean isBackOf(final Square to, final Side side) {
        if (side == Side.WHITE) {
            return to.rank.isBiggerThan(this.rank);
        }
        return this.rank.isBiggerThan(to.rank);
    }

    public List<Square> squaresOfPath(Square to) {
        if (isLine(to)) {
            return squaresOfLine(to);
        }
        if (isDiagonal(to)) {
            return squaresOfDiagonal(to);
        }
        return Collections.EMPTY_LIST;
    }

    private boolean isLine(final Square to) {
        validateNotSameSquare(to);
        return this.rank == to.rank || this.file == to.file;
    }

    private boolean isDiagonal(final Square to) {
        validateNotSameSquare(to);
        final int verticalDistance = this.rank.distanceTo(to.rank);
        final int horizontalDistance = this.file.distanceTo(to.file);
        return verticalDistance == horizontalDistance;
    }

    private List<Square> squaresOfLine(final Square to) {
        if (!isLine(to)) {
            throw new IllegalArgumentException("직선이 아닙니다");
        }
        if (to.rank == this.rank) {
            return File.filesBetween(this.file, to.file)
                       .stream()
                       .map(foundFile -> Square.of(rank, foundFile))
                       .collect(Collectors.toUnmodifiableList());
        }
        return Rank.ranksBetween(this.rank, to.rank)
                   .stream()
                   .map(foundRank -> Square.of(foundRank, file))
                   .collect(Collectors.toUnmodifiableList());
    }

    private List<Square> squaresOfDiagonal(final Square to) {
        if (!isDiagonal(to)) {
            throw new IllegalArgumentException("대각선이 아닙니다");
        }
        List<Rank> ranks = Rank.ranksBetween(this.rank, to.rank);
        List<File> files = File.filesBetween(this.file, to.file);
        List<Square> squares = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++) {
            squares.add(Square.of(ranks.get(i), files.get(i)));
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

    public int getRank() {
        return rank.getPosition();
    }

    public File getFile() {
        return file;
    }
}
