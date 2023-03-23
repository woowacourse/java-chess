package chess.domain.movement;

import static java.lang.Math.abs;

public enum Continuity {
    CONTINUOUS,
    DISCONTINUOUS;

    private static final int DISCONTINUOUS_LIMIT = 1;

    public static Continuity of(final int fileInterval, final int rankInterval) {
        if (abs(fileInterval) > DISCONTINUOUS_LIMIT || abs(rankInterval) > DISCONTINUOUS_LIMIT) {
            return CONTINUOUS;
        }
        return DISCONTINUOUS;
    }
}
