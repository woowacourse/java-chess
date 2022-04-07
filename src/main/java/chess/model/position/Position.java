package chess.model.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {
    private static final Map<String, Position> CACHE_POSITION;

    static {
        CACHE_POSITION = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank)))
                .collect(Collectors.toMap(Position::getKey, p -> p));
    }

    private final Rank rank;
    private final File file;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String position) {
        if (CACHE_POSITION.containsKey(position)) {
            return CACHE_POSITION.get(position);
        }
        throw new IllegalArgumentException("유효하지 않은 위치입니다.");
    }

    public static Position of(File file, Rank rank) {
        return CACHE_POSITION.get(getKey(file, rank));
    }

    private static String getKey(File file, Rank rank) {
        return file.getValue() + rank.getValue();
    }

    public int getRankGap(Position other) {
        return rank.absMinus(other.rank);
    }

    public int getFileGap(Position other) {
        return file.absMinus(other.file);
    }

    public Position getNext(Direction direction) {
        File nextFile = file.getNext(direction.getFileGap());
        Rank nextRank = rank.getNext(direction.getRankGap());

        return Position.of(nextFile, nextRank);
    }

    public boolean isInitPawn(Direction direction) {
        return (direction == Direction.N && rank == Rank.TWO)
                || (direction == Direction.S && rank == Rank.SEVEN);
    }

    public boolean isLastFile() {
        return file.equals(File.H);
    }

    public String getPosition() {
        return file.getValue() + rank.getValue();
    }

    public int getFileGapDividedByGcd(Position other) {
        return file.minus(other.file) / getGcd(other);
    }

    public int getRankGapDividedByGcd(Position other) {
        return rank.minus(other.rank) / getGcd(other);
    }

    private int getGcd(Position other) {
        int rankGap = rank.absMinus(other.rank);
        int fileGap = file.absMinus(other.file);
        if (rankGap == 0) {
            return fileGap;
        }
        if (fileGap == 0) {
            return rankGap;
        }
        return Math.min(fileGap, rankGap);
    }

    private String getKey() {
        return file.getValue() + rank.getValue();
    }

    @Override
    public int compareTo(Position position) {
        if (isLessRankThan(position)) {
            return 1;
        }
        if (isFileComparison(position)) {
            return 1;
        }
        return -1;
    }

    private boolean isBiggerFileThan(Position position) {
        return this.file.isBiggerThan(position.file);
    }

    private boolean isLessRankThan(Position position) {
        return this.rank.isLessThan(position.rank);
    }

    private boolean isFileComparison(Position position) {
        return getRank() == position.getRank() && isBiggerFileThan(position);
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
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
