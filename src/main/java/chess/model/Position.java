package chess.model;

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

    public static Position of(char... position) {
        String key = position[0] + String.valueOf(position[1]);
        if (CACHE_POSITION.containsKey(key)) {
            return CACHE_POSITION.get(key);
        }
        throw new IllegalArgumentException("유효하지 않은 위치입니다.");
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

    public boolean isOneStepAway(Position position) {
        return isOneStepHorizontalOrVertical(position) || isOneStepDiagonal(position);
    }

    public boolean isKnightDirection(Position position) {
        return isTwoFileOneRankStep(position) || isOneFileTwoRankStep(position);
    }

    public boolean isInitPawnPosition(Rank rank) {
        return this.rank.equals(rank);
    }

    public boolean isStepForward(Position position, int direction, int distance) {
        return position.rank.minus(rank) == direction * distance && file.absMinus(position.file) == 0;
    }

    public boolean isBiggerFileThan(Position position) {
        return this.file.isBiggerThan(position.file);
    }

    public boolean isLessRankThan(Position position) {
        return this.rank.isLessThan(position.rank);
    }

    public boolean isLastFile() {
        return file.equals(File.H);
    }

    public boolean isTwoStepAway(Position position) {
        return rank.absMinus(position.rank) == 2;
    }

    public Position getUpVerticalPosition(int distance) {
        return new Position(file, rank.getNext(distance));
    }

    public boolean isOneStepDiagonal(Position position, int forwardDirection) {
        return position.rank.minus(rank) == forwardDirection && file.absMinus(position.file) == 1;
    }

    private boolean isOneFileTwoRankStep(Position position) {
        return rank.absMinus(position.rank) == 2 && file.absMinus(position.file) == 1;
    }

    private boolean isTwoFileOneRankStep(Position position) {
        return file.absMinus(position.file) == 2 && rank.absMinus(position.rank) == 1;
    }

    private boolean isOneStepHorizontalOrVertical(Position position) {
        return rank.absMinus(position.rank) + file.absMinus(position.file) == 1;
    }

    private boolean isOneStepDiagonal(Position position) {
        return rank.absMinus(position.rank) == 1 && file.absMinus(position.file) == 1;
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

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }

    public Position getNext(Direction direction) {
        File nextFile = file.getNext(direction.getFileGap());
        Rank nextRank = rank.getNext(direction.getRankGap());

        return Position.of(nextFile, nextRank);
    }

    private String getKey() {
        return file.getValue() + rank.getValue();
    }
}
