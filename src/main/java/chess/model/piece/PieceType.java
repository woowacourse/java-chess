package chess.model.piece;

import java.util.Arrays;

public enum PieceType {
    WHITE_PAWN("p", 0),
    WHITE_ROOK("r", 0),
    WHITE_KNIGHT("n", 0),
    WHITE_BISHOP("b", 0),
    WHITE_QUEEN("q", 0),
    WHITE_KING("k", 0),
    BLACK_PAWN("P", 1),
    BLACK_ROOK("R", 1),
    BLACK_KNIGHT("N", 1),
    BLACK_BISHOP("B", 1),
    BLACK_QUEEN("Q", 1),
    BLACK_KING("K", 1),
    NONE(".", 2);

    private static final int COLOR_COUNT = 2;

    private final String displayName;
    private final int colorId;

    PieceType(String displayName, int colorId) {
        this.displayName = displayName;
        this.colorId = colorId;
    }

    public static PieceType findPieceTypeByName(String name) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.displayName.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 기물 이름입니다."));
    }

    public boolean isSameColor(int turnCount) {
        return colorId == (turnCount % COLOR_COUNT);
    }

    public boolean isWhite() {
        return colorId == 0;
    }

    public boolean isBlack() {
        return colorId == 1;
    }

    public String getDisplayName() {
        return displayName;
    }
}
