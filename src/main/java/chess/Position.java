package chess;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final ChessFile chessFile;
    private final ChessRank chessRank;

    static {
        for(ChessFile chessFile : ChessFile.values()) {
            for (ChessRank chessRank : ChessRank.values()) {
                POSITIONS.put(key(chessFile, chessRank), new Position(chessFile, chessRank));
            }
        }
    }

    public Position(ChessFile chessFile, ChessRank chessRank) {
        this.chessFile = chessFile;
        this.chessRank = chessRank;
    }

    public static Position of(ChessFile chessFile, ChessRank chessRank){
        return POSITIONS.getOrDefault(key(chessFile,chessRank),new Position(chessFile,chessRank));
    }

    // TODO: 2020/03/24 default 생성 고려하기.
    public static Position of(String key) {
        return POSITIONS.get(key);
    }

    private static String key(ChessFile chessFile, ChessRank chessRank) {
        return String.format("%s%s", chessFile, chessRank);
    }

    @Override
    public String toString() {
        return key(this.chessFile, this.chessRank);
    }
}
