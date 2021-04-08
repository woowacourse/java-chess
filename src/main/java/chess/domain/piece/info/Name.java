package chess.domain.piece.info;

import java.util.Arrays;

public enum Name {
    BISHOP("B"),
    KING("K"),
    KNIGHT("N"),
    PAWN("P"),
    QUEEN("Q"),
    ROOK("R"),
    NONE(".");

    private final String name;

    Name(String name) {
        this.name = name;
    }

    public static Name findPieceTypeByName(String name) {
        String upperName = name.toUpperCase();
        return Arrays.stream(values())
                .filter(nameType -> nameType.name.equals(upperName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 매칭되는 이름이 없습니다."));
    }

    public String nameByColor(Color color) {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }
}
