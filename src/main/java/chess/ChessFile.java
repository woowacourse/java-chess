package chess;

import java.util.HashMap;
import java.util.Map;

public class ChessFile {
    private static final Map<Character, ChessFile> CHESS_FILES = new HashMap<>();

    private final char chessFile;

    static {
        for (char c = 'a'; c <= 'h'; c++) {
            CHESS_FILES.put(c, new ChessFile(c));
        }
    }

    private ChessFile(char chessFile) {
        validate(chessFile);
        this.chessFile = chessFile;
    }

    public static ChessFile of(char chessFile) {
        return CHESS_FILES.getOrDefault(chessFile, new ChessFile(chessFile));
    }

    private void validate(char chessFile) {
        if (chessFile < 'a' || chessFile > 'h') {
            throw new IllegalArgumentException("유효한 체스 파일이 아닙니다.");
        }
    }

}
