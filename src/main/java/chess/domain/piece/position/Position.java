package chess.domain.piece.position;

import static chess.domain.piece.position.PositionUtil.fileToIdx;
import static chess.domain.piece.position.PositionUtil.rankToIdx;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private final int fileIdx;
    private final int rankIdx;

    private Position(String value) {
        char[] positionInfo = value.toCharArray();
        this.fileIdx = fileToIdx(positionInfo[0]); //a / 2 => 2행 BUT 2열
        this.rankIdx = rankToIdx(positionInfo[1]); //2 / a => 1열 BUT 1행
    }

    public static Position of(String value) {
        return PositionCache.getCache(value);
    }

    public int getFileIdx() {
        return fileIdx;
    }

    public int getRankIdx() {
        return rankIdx;
    }

    @Override
    public String toString() {
        return "Position{" +
                "fileIdx=" + fileIdx +
                ", rankIdx=" + rankIdx +
                '}';
    }

    private static class PositionCache {

        static Map<String, Position> cache = new HashMap<>(64);

        static Position getCache(String value) {
            return cache.computeIfAbsent(value, Position::new);
        }
    }
}
