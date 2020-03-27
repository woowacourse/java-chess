package chess.team;

import java.util.Arrays;

public enum Team {
    BLACK(true),
    WHITE(false);

    private final boolean isBlack;

    Team(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }
}
