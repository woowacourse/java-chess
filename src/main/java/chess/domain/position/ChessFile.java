package chess.domain.position;

import java.util.*;

public class ChessFile {

    private static final char LOWER_BOUND = 'a';
    private static final char UPPER_BOUND = 'h';
    private static final Map<Character, ChessFile> CHESS_FILE_CACHE = new HashMap<>();

    private final char chessFile;


    static {
        for (char character = LOWER_BOUND; character <= UPPER_BOUND; character++) {
            CHESS_FILE_CACHE.put(character, new ChessFile(character));
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
        return CHESS_FILE_CACHE.getOrDefault(chessFile, new ChessFile(chessFile));
    }

    public static List<ChessFile> values() {
        return new ArrayList<>(CHESS_FILE_CACHE.values());
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
