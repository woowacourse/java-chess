package chess.domain.movement;

public enum Continuity {
    CONTINUOUS,
    DISCONTINUOUS;

    private static final int DISCONTINUOUS_LIMIT = 1;

    public static Continuity of(final int fileInterval, final int rankInterval) {
        if (Math.abs(fileInterval) > DISCONTINUOUS_LIMIT || Math.abs(rankInterval) > DISCONTINUOUS_LIMIT) {
            return CONTINUOUS;
        }
        return DISCONTINUOUS;
    }
}
