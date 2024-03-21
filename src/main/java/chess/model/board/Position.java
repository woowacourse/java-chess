package chess.model.board;

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

    public static Position from(int file, int rank) {
        int key = convertToKey(file, rank);
        if (!POSITION_CACHE.containsKey(key)) {
            throw new IllegalArgumentException("체스판 범위를 벗어난 좌표값입니다.");
        }
        return POSITION_CACHE.get(key);
    }

    public static List<Position> values() {
        return List.copyOf(POSITION_CACHE.values());
    }

    public int getFileGap(Position other) {
        return other.file - file;
    }

    public int getRankGap(Position other) {
        return other.rank - rank;
    }

    public Position moveToTargetByStep(Position target) {
        int fileStep = Integer.signum(getFileGap(target));
        int rankStep = Integer.signum(getRankGap(target));
        return Position.from(file + fileStep, rank + rankStep);
    }

    public boolean isRankEquals(int rank) {
        return this.rank == rank;
    }
}
