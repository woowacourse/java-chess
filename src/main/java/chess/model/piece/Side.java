package chess.model.piece;

import java.util.List;

public enum Side {
    BLACK,
    WHITE,
    NONE;

    public static List<Side> colors() {
        return List.of(BLACK, WHITE);
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public Side getOppositeSide() {
        if (isWhite()) {
            return BLACK;
        }
        if (isBlack()) {
            return WHITE;
        }
        return NONE;
    }
}
