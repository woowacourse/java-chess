package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int POSITION_KEY_VALID_LENGTH = 2;
    private static final Map<String, Position> POSITION_CACHE = new HashMap<>();

    static {
        for (ChessFile chessFile : ChessFile.values()) {
            for (ChessRank chessRank : ChessRank.values()) {
                POSITION_CACHE.put(key(chessFile, chessRank), new Position(chessFile, chessRank));
            }
        }
    }

    private final ChessFile chessFile;
    private final ChessRank chessRank;

    private Position(ChessFile chessFile, ChessRank chessRank) {
        this.chessFile = chessFile;
        this.chessRank = chessRank;
    }

    private Position(String key) {
        this(ChessFile.from(key.charAt(FILE_INDEX)), ChessRank.from(key.charAt(RANK_INDEX)));
    }

    public static Position of(ChessFile chessFile, ChessRank chessRank) {
        Objects.requireNonNull(chessFile, "유효한 체스파일이 아닙니다.");
        Objects.requireNonNull(chessRank, "유효한 체스랭크가 아닙니다.");

        return POSITION_CACHE.getOrDefault(key(chessFile, chessRank), new Position(chessFile, chessRank));
    }

    public static Position of(String key) {
        validate(key);
        return POSITION_CACHE.getOrDefault(key, new Position(key));
    }

    private static void validate(String key) {
        validateEmpty(key);
        validateLength(key);
    }

    private static void validateEmpty(String key) {
        if (Objects.isNull(key) || key.isEmpty()) {
            throw new IllegalArgumentException("유효한 위치 입력이 아닙니다.");
        }
    }

    private static void validateLength(String key) {
        if (key.length() != POSITION_KEY_VALID_LENGTH) {
            throw new IllegalArgumentException("유효한 위치 입력이 아닙니다.");
        }
    }

    private static String key(ChessFile chessFile, ChessRank chessRank) {
        return String.format("%s%s", chessFile.getChessFile(), chessRank.getChessRank());
    }

    public Position move(int fileMovingUnit, int rankMovingUnit) {
        return Position.of(chessFile.move(fileMovingUnit), chessRank.move(rankMovingUnit));
    }

    public int calculateChessFileGapTo(Position targetPosition) {
        Objects.requireNonNull(targetPosition, "비교할 타겟 위치가 존재하지 않습니다.");
        return this.chessFile.intervalTo(targetPosition.chessFile);
    }

    public int calculateChessRankGapTo(Position targetPosition) {
        Objects.requireNonNull(targetPosition, "비교할 타겟 위치가 존재하지 않습니다.");
        return this.chessRank.intervalTo(targetPosition.chessRank);
    }

    public boolean isSameFilePosition(ChessFile chessFile) {
        return this.chessFile.equals(chessFile);
    }

    @Override
    public String toString() {
        return key(this.chessFile, this.chessRank);
    }
}
