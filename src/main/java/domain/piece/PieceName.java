package domain.piece;

import java.util.Arrays;

public enum PieceName {

    PAWN("P", "p", 1),
    ROOK("R", "r", 5),
    KNIGHT("N", "n", 2.5),
    BISHOP("B", "b", 3),
    QUEEN("Q", "q", 9),
    KING("K", "k", 0);

    private final String black;
    private final String white;
    private final double score;

    PieceName(String black, String white, double score) {
        this.black = black;
        this.white = white;
        this.score = score;
    }

    public static PieceName of(String name) {
        if (Character.isUpperCase(name.charAt(0))) {
            return getPieceNameOfBlack(name);
        }
        return getPieceNameOfWhite(name);
    }

    private static PieceName getPieceNameOfBlack(String name) {
        return Arrays.stream(values())
                .filter(pieceName -> pieceName.getBlack().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 말 이름입니다."));
    }

    private static PieceName getPieceNameOfWhite(String name) {
        return Arrays.stream(values())
                .filter(pieceName -> pieceName.getWhite().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 말 이름입니다."));
    }

    public String getBlack() {
        return black;
    }

    public String getWhite() {
        return white;
    }

    public double getScore() {
        return score;
    }
}
