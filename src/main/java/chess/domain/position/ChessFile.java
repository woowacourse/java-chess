package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

public enum ChessFile {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private char chessFile;

    ChessFile(char chessFile) {
        this.chessFile = chessFile;
    }

    public static ChessFile findValueOf(char inputChessFile) {
        return Arrays.stream(ChessFile.values())
                .filter(chessFile2 -> chessFile2.getChessFile() == inputChessFile)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("움직을 수 없습니다."));
    }

    public ChessFile move(int fileMovingUnit) {
        char movingChessFile = (char) (this.chessFile + fileMovingUnit);
        return findValueOf(movingChessFile);
    }

    public int intervalTo(ChessFile targetFile) {
        Objects.requireNonNull(targetFile, "비교할 타겟 파일이 존재하지 않습니다.");
        return targetFile.chessFile - this.chessFile;
    }

    public char getChessFile() {
        return chessFile;
    }
}
