package chess.domain.piece;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Position {

    private static final Set<Position> POSITIONS = cachePositions();

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static Set<Position> cachePositions() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> new Position(file, rank)))
                .collect(Collectors.toSet());
    }

    private static Position findPosition(final File file, final Rank rank) {
        return POSITIONS.stream()
                .filter(position -> position.equals(new Position(file, rank)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 위치입니다."));
    }

    public static Position from(final String input) {
        String file = input.substring(0, 1);
        String rank = input.substring(1);
        return findPosition(File.fromSymbol(file), Rank.fromInput(rank));
    }

    public boolean isVerticalWith(final Position target) {
        return this.file == target.file && this.rank != target.rank;
    }

    public boolean isHorizontalWith(final Position target) {
        return this.file != target.file && this.rank == target.rank;
    }

    public boolean isDiagonalWith(final Position target) {
        return this.file.getDistance(target.file) == this.rank.getDistance(target.rank);
    }

    public boolean isUpWith(final Position target) {
        return this.rank.isBigger(target.rank);
    }

    public boolean isDownWith(final Position target) {
        return !isUpWith(target);
    }

    public boolean isRightWith(final Position target) {
        return this.file.isBigger(target.file);
    }

    public boolean isLeftWith(final Position target) {
        return !isRightWith(target);
    }

    public Position up() {
        return new Position(this.file, this.rank.up());
    }

    public Position right() {
        return new Position(this.file.right(), this.rank);
    }

    public Position upRight() {
        return new Position(this.file.right(), this.rank.up());
    }

    public Position upLeft() {
        return new Position(this.file.left(), this.rank.up());
    }

    public int getRankDistance(final Position target) {
        return this.rank.getDistance(target.rank);
    }

    public int getFileDistance(final Position target) {
        return this.file.getDistance(target.file);
    }

    public boolean isRank(final Rank rank) {
        return this.rank.equals(rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "" + file + rank;
    }
}
