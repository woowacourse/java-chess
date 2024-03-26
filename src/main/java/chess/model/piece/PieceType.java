package chess.model.piece;

import java.util.Arrays;

public enum PieceType {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    NONE(".");

    private final String displayName;

    PieceType(String displayName) {
        this.displayName = displayName;
    }

    public static PieceType findPieceTypeByName(String name) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.displayName.equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 기물 이름입니다."));
    }

    public boolean isNone() {
        return this == NONE;
    }

    public String getDisplayName(Color color) {
        if (color.isBlack()) {
            return displayName.toUpperCase();
        }
        return displayName;
    }
}
