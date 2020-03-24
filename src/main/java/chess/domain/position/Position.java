package chess.domain.position;

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

    public Position(String key){
        this(ChessFile.from(key.charAt(0)),ChessRank.from(Character.getNumericValue(key.charAt(1))));
    }

    public static Position of(ChessFile chessFile, ChessRank chessRank){
        return POSITIONS.getOrDefault(key(chessFile,chessRank),new Position(chessFile,chessRank));
    }

    public static Position of(String key) {
        return POSITIONS.getOrDefault(key, new Position(key));
    }

    private static String key(ChessFile chessFile, ChessRank chessRank) {
        return String.format("%s%s", chessFile, chessRank);
    }

    @Override
    public String toString() {
        return key(this.chessFile, this.chessRank);
    }
}
