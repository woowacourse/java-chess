package chess.domain.chess;

import java.util.Arrays;

public enum CampType {
    BLACK,
    WHITE;

    private static final String ERROR_MESSAGE = "일치하는 진영이 존재하지 않습니다.";

    public static CampType divide(final char columnPosition) {
        if (Character.isLowerCase(columnPosition)) {
            return WHITE;
        }
        return BLACK;
    }

    public static CampType from(final String name) {
        return Arrays.stream(CampType.values())
                .filter(campType -> campType.name().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(ERROR_MESSAGE);
                });
    }

    public CampType changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
