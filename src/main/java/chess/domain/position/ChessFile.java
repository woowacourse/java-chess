package chess.domain.position;

import java.util.*;

public class ChessFile {

    private static final Map<Character, ChessFile> CHESS_FILES = new HashMap<>();
    private static final char LOWER_BOUND = 'a';
    private static final char UPPER_BOUND = 'h';

    private final char chessFile;


    static {
        for (char c = LOWER_BOUND; c <= UPPER_BOUND; c++) {
            CHESS_FILES.put(c, new ChessFile(c));
        }
    }

    private ChessFile(char chessFile) {
        validate(chessFile);
        this.chessFile = chessFile;
    }

    private void validate(char chessFile) {
        if (chessFile < LOWER_BOUND || chessFile > UPPER_BOUND) {
            throw new IllegalArgumentException("유효한 체스 파일이 아닙니다.");
        }
    }

    public static ChessFile from(char chessFile) {
        return CHESS_FILES.getOrDefault(chessFile, new ChessFile(chessFile));
    }

    public static List<ChessFile> values() {
        return new ArrayList<>(CHESS_FILES.values());
    }


    public ChessFile move(int fileMovingUnit) {
        return from((char) (this.chessFile + fileMovingUnit));
    }

    public int intervalTo(ChessFile targetFile) {
        Objects.requireNonNull(targetFile, "비교할 타겟 파일이 존재하지 않습니다.");
        return targetFile.chessFile - this.chessFile;
    }

    @Override
    public String toString() {
        return String.valueOf(chessFile);
    }
}
