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


    public boolean inLine(final Square to) {
        checkSameSquare(to);
        return this.rank == to.rank || this.file == to.file;
    }

    public boolean inDiagonal(final Square to) {
        checkSameSquare(to);
        final int verticalDistance = this.rank.distanceTo(to.rank);
        final int horizontalDistance = this.file.distanceTo(to.file);
        return verticalDistance == horizontalDistance;
    }

    public boolean inLShape(final Square to) {
        checkSameSquare(to);
        final int verticalDistance = this.rank.distanceTo(to.rank);
        final int horizontalDistance = this.file.distanceTo(to.file);
        if (verticalDistance == 0 || horizontalDistance == 0) {
            return false;
        }
        return verticalDistance + horizontalDistance == 3;
    }

    public boolean inPawnsInitialMovableRange(final Square to, final Side side) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        return isBackOf(to, side) &&
                (verticalDistance == 1 || verticalDistance == 2) &&
                file.distanceTo(to.file) == 0;
    }

    public boolean inPawnsMovableRange(final Square to, final Side side) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        return isBackOf(to, side) &&
                verticalDistance == 1 &&
                file.distanceTo(to.file) == 0;
    }

    public boolean inPawnsCatchableRange(final Square to, final Side side) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        return isBackOf(to, side) &&
                verticalDistance == 1 &&
                file.distanceTo(to.file) == 1;
    }

    private boolean isBackOf(final Square to, final Side side) {
        if (side == Side.WHITE) {
            return to.rank.isBiggerThan(this.rank);
        }
        return this.rank.isBiggerThan(to.rank);
    }

    public boolean inKingsRange(final Square to) {
        checkSameSquare(to);
        final int verticalDistance = rank.distanceTo(to.rank);
        final int horizontalDistance = file.distanceTo(to.file);
        return (verticalDistance == 1 || verticalDistance == 0) &&
                (horizontalDistance == 1 || horizontalDistance == 0);
    }

    private void checkSameSquare(final Square to) {
        if (this == to) {
            throw new IllegalArgumentException("같은 지점이 들어왔습니다");
        }
    }

    public List<Square> squaresOfPath(Square to) {
        if (inLine(to)) {
            return squaresOfLine(to);
        }
        if (inDiagonal(to)) {
            return squaresOfDiagonal(to);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Square> squaresOfLine(final Square to) {
        if (!inLine(to)) {
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
        if (!inDiagonal(to)) {
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

    public int getRank() {
        return rank.getPosition();
    }

    public File getFile() {
        return file;
    }
}
