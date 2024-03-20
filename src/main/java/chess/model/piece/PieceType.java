package chess.model.piece;


import java.util.Arrays;

public enum PieceType {
    BLACK_PAWN("P", 1),
    BLACK_ROOK("R", 1),
    BLACK_KNIGHT("N", 1),
    BLACK_BISHOP("B", 1),
    BLACK_QUEEN("Q", 1),
    BLACK_KING("K", 1),
    WHITE_PAWN("p", 2),
    WHITE_ROOK("r", 2),
    WHITE_KNIGHT("n", 2),
    WHITE_BISHOP("b", 2),
    WHITE_QUEEN("q", 2),
    WHITE_KING("k", 2),
    NONE(".", 0);

    private final String displayName;
    private final int side;

    PieceType(String displayName, int side) {
        this.displayName = displayName;
        this.side = side;
    }

    public static PieceType findPieceTypeByName(String name) {
        return Arrays.stream(values())
            .filter(pieceType -> pieceType.displayName.equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 기물 이름입니다."));
    }

    public String getDisplayName() {
        return displayName;
    }
}
