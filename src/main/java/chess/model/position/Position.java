package chess.model.position;

import chess.model.board.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final Map<Integer, Position> POSITION_CACHE = new HashMap<>();

    static {
        for (int file = Board.MIN_LENGTH; file <= Board.MAX_LENGTH; file++) {
            addPositionsBy(file);
        }
    }

    private static void addPositionsBy(int file) {
        for (int rank = Board.MIN_LENGTH; rank <= Board.MAX_LENGTH; rank++) {
            POSITION_CACHE.put(convertToKey(file, rank), new Position(file, rank));
        }
    }

    private static int convertToKey(int file, int rank) {
        return Objects.hash(file, rank);
    }

    private final int file;

    private final int rank;

    private Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(int file, int rank) {
        int key = convertToKey(file, rank);
        if (!POSITION_CACHE.containsKey(key)) {
            throw new IllegalArgumentException("체스판 범위를 벗어난 좌표값입니다.");
        }
        return POSITION_CACHE.get(key);
    }

    public int getFileGap(Position other) {
        return other.file - file;
    }

    public int getRankGap(Position other) {
        return other.rank - rank;
    }

    public boolean isSameFile(Position other) {
        return file == other.file;
    }

    public boolean isSameRank(Position other) {
        return rank == other.rank;
    }

    public Position moveToTargetByStep(Position target) {
        int fileStep = Integer.signum(target.file - file);
        int rankStep = Integer.signum(target.rank - rank);
        return Position.of(file + fileStep, rank + rankStep);
    }

    public static List<Position> values() {
        return List.copyOf(POSITION_CACHE.values());
    }

    public boolean isOnRank(int rank) {
        return this.rank == rank;
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
