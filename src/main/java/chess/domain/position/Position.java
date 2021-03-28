package chess.domain.position;

import chess.domain.piece.Direction;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final Map<String, Position> POSITIONS = new LinkedHashMap<>();

    static {
        for (Rank rankValue : Rank.values()) {
            initializePosition(rankValue);
        }
    }

    private final Rank rank;
    private final File file;

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    private static void initializePosition(Rank rankValue) {
        for (File fileValue : File.values()) {
            POSITIONS.put(rankValue.getRank() + fileValue.getFile(), new Position(rankValue, fileValue));
        }
    }

    public static Map<String, Position> getPositions() {
        return Collections.unmodifiableMap(POSITIONS);
    }

    public static Position valueOf(final Rank rank, final File file) {
        return POSITIONS.get(rank.getRank() + file.getFile());
    }

    public static Position valueOf(final String rank, final String file) {
        Rank findRank = Rank.findByRank(rank);
        File findFile = File.findByFile(file);

        return valueOf(findRank, findFile);
    }

    public static Position valueOf(final int rank, final int file) {
        Rank findRank = Rank.findByValue(rank);
        File findFile = File.findByValue(file);

        return POSITIONS.get(findRank.getRank() + findFile.getFile());
    }

    public Position findNextPosition(final Direction direction) {
        int nextRank = this.getRankValue() + direction.getRow();
        int nextFile = this.getFileValue() + direction.getColumn();
        return findNextPositionByDirection(direction, nextRank, nextFile);
    }

    private Position findNextPositionByDirection(final Direction direction,
                                                 final int nextRank, final int nextFile) {
        if (isRankInBound(nextRank) && isFileInBound(nextFile)) {
            Rank findRank = Rank.findByValue(this.getRankValue() + direction.getRow());
            File findFile = File.findByValue(this.getFileValue() + direction.getColumn());
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    public Position findNextPosition(final Direction direction, final int index) {
        int nextRank = this.getRankValue() + direction.getRow() + index;
        int nextFile = this.getFileValue() + direction.getColumn();
        return findNextPositionByDirection(direction, nextRank, nextFile, index);
    }

    private Position findNextPositionByDirection(final Direction direction,
                                                 final int nextRank, final int nextFile, final int index) {
        if (isRankInBound(nextRank) && isFileInBound(nextFile)) {
            Rank findRank = Rank.findByValue(this.getRankValue() + direction.getRow() + index);
            File findFile = File.findByValue(this.getFileValue() + direction.getColumn());
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    public Position findNextPositionByRank(final Direction direction, final int index) {
        int nextRank = direction.getRow() + index;
        int nextFile = this.getFileValue() + direction.getColumn();
        return findNextPositionByRank(direction, nextRank, nextFile, index);
    }

    private Position findNextPositionByRank(final Direction direction,
                                            final int nextRank, final int nextFile, final int index) {
        if (isRankInBound(nextRank) && isFileInBound(nextFile)) {
            Rank findRank = Rank.findByValue(direction.getRow() + index);
            File findFile = File.findByValue(this.getFileValue() + direction.getColumn());
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    public Position findNextPositionByFile(final Direction direction, final int index) {
        int nextRank = this.getRankValue() + direction.getRow();
        int nextFile = direction.getColumn() + index;
        return findNextPositionByFile(direction, nextRank, nextFile, index);
    }

    private Position findNextPositionByFile(final Direction direction,
                                            final int nextRank, final int nextFile, final int index) {
        if (isRankInBound(nextRank) && isFileInBound(nextFile)) {
            Rank findRank = Rank.findByValue(this.getRankValue() + direction.getRow());
            File findFile = File.findByValue(direction.getColumn() + index);
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    private boolean isRankInBound(final int rank) {
        return 1 <= rank && rank <= 8;
    }

    private boolean isFileInBound(final int file) {
        return 1 <= file && file <= 8;
    }

    public static Position from(final String source) {
        String reversedSource = reverse(source);
        if (Objects.isNull(POSITIONS.get(reversedSource))) {
            throw new IllegalArgumentException("없는 위치입니다!");
        }
        return POSITIONS.get(reversedSource);
    }

    private static String reverse(final String source) {
        String reversedSource = "";
        reversedSource += source.charAt(1);
        reversedSource += source.charAt(0);
        return reversedSource;
    }

    public Rank getRank() {
        return rank;
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public File getFile() {
        return file;
    }

    public int getFileValue() {
        return file.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return file.getFile() + rank.getRank();
    }
}
