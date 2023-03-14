package chess.domain;

import java.util.Arrays;

public enum PieceType {
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    PAWN("p"),
    EMPTY(".");
    private final String type;

    PieceType(String type) {
        this.type = type;
    }

    public static PieceType from(String type) {
        return Arrays.stream(PieceType.values())
                .filter(it -> it.type.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public String getType() {
        return type;
    }
}
