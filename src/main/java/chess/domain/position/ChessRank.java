package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

public enum ChessRank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private int chessRank;

    ChessRank(int chessRank) {
        this.chessRank = chessRank;
    }

    public static ChessRank findValueOf(char inputChessRank) {
        return Arrays.stream(ChessRank.values())
                .filter(chessRank2 -> chessRank2.getChessRank() == Character.getNumericValue(inputChessRank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("움직을 수 없습니다."));
    }

    public static ChessRank findValueOf(int inputChessRank) {
        return Arrays.stream(ChessRank.values())
                .filter(chessRank2 -> chessRank2.getChessRank() == inputChessRank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("움직을 수 없습니다."));
    }

    public ChessRank move(int fileMovingUnit) {
        int movingChessRank = (this.chessRank + fileMovingUnit);
        return findValueOf(movingChessRank);
    }

    public int intervalTo(ChessRank targetRank) {
        Objects.requireNonNull(targetRank, "비교할 타겟 랭크가 존재하지 않습니다.");
        return targetRank.chessRank - this.chessRank;
    }

    public int getChessRank() {
        return chessRank;
    }
}
