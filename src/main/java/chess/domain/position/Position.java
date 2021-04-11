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

    public Position findTargetPosition(final Direction direction) {
        int targetRank = this.getRankValue() + direction.getRow();
        int targetFile = this.getFileValue() + direction.getColumn();
        return findTargetPositionByDirection(direction, targetRank, targetFile);
    }

    private Position findTargetPositionByDirection(final Direction direction,
                                                   final int targetRank, final int targetFile) {
        if (isRankInBound(targetRank) && isFileInBound(targetFile)) {
            Rank findRank = Rank.findByValue(this.getRankValue() + direction.getRow());
            File findFile = File.findByValue(this.getFileValue() + direction.getColumn());
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    public Position findTargetPosition(final Direction direction, final int index) {
        int targetRank = this.getRankValue() + direction.getRow() + index;
        int targetFile = this.getFileValue() + direction.getColumn();
        return findTargetPositionByDirection(direction, targetRank, targetFile, index);
    }

    private Position findTargetPositionByDirection(final Direction direction,
                                                   final int targetRank, final int targetFile, final int index) {
        if (isRankInBound(targetRank) && isFileInBound(targetFile)) {
            Rank findRank = Rank.findByValue(this.getRankValue() + direction.getRow() + index);
            File findFile = File.findByValue(this.getFileValue() + direction.getColumn());
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    public Position findTargetPositionByRank(final Direction direction, final int index) {
        int targetRank = direction.getRow() + index;
        int targetFile = this.getFileValue() + direction.getColumn();
        return findTargetPositionByRank(direction, targetRank, targetFile, index);
    }

    private Position findTargetPositionByRank(final Direction direction,
                                              final int targetRank, final int targetFile, final int index) {
        if (isRankInBound(targetRank) && isFileInBound(targetFile)) {
            Rank findRank = Rank.findByValue(direction.getRow() + index);
            File findFile = File.findByValue(this.getFileValue() + direction.getColumn());
            return POSITIONS.get(findRank.getRank() + findFile.getFile());
        }
        return this;
    }

    public Position findTargetPositionByFile(final Direction direction, final int index) {
        int targetRank = this.getRankValue() + direction.getRow();
        int targetFile = direction.getColumn() + index;
        return findTargetPositionByFile(direction, targetRank, targetFile, index);
    }

    private Position findTargetPositionByFile(final Direction direction,
                                              final int targetRank, final int targetFile, final int index) {
        if (isRankInBound(targetRank) && isFileInBound(targetFile)) {
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

    public static Position findByString(final String positionString) {
        return Position.valueOf(
                Rank.findByRank(positionString.substring(1, 2)),
                File.findByFile(positionString.substring(0, 1)));
    }

    public File getFile() {
        return file;
    }

    public int getRankValue() {
        return rank.getValue();
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
