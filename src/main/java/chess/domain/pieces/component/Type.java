package chess.domain.pieces.component;

import java.util.Arrays;

public enum Type {
    PAWN("P", 1.0),
    ROOK("R", 5.0),
    KNIGHT("N", 2.5),
    BISHOP("B", 3.0),
    QUEEN("Q", 9.0),
    KING("K", 0.0),
    NO_PIECE(".", 0.0);

    private final String name;
    private final double score;

    Type(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public static Type of(final String inputName) {
        if (inputName != ".") {
            inputName.toUpperCase();
        }
        return Arrays.stream(Type.values())
                .filter(type -> type.name.equals(inputName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 갈 수 없는 방향입니다."));
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
