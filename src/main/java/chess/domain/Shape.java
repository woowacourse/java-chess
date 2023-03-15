package chess.domain;

import java.util.Arrays;

public enum Shape {

    PAWN('p', 'P'),
    KING('k', 'K'),
    QUEEN('q', 'Q'),
    ROOK('r', 'R'),
    BISHOP('b', 'B'),
    KNIGHT('n', 'N');

    private final char whiteName;
    private final char blackName;

    Shape(char whiteName, char blackName) {
        this.whiteName = whiteName;
        this.blackName = blackName;
    }

    public static Shape findByWhiteName(char whiteName) {
        return Arrays.stream(values())
                .filter(shape -> shape.whiteName == whiteName)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

}
