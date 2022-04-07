package chess.domain;

import java.util.Arrays;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isSame(Team other) {
        return this.equals(other);
    }

    public Team reverse() {
        if (isWhite()) {
            return BLACK;
        }
        return WHITE;
    }
}
