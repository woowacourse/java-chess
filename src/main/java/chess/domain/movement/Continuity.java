package chess.domain.movement;

public enum Continuity {
    CONTINUOUS,
    DISCONTINUOUS;

    private static final int CONTINUITY_BOUNDARY = 1;

    public static Continuity of(final int fileInterval, final int rankInterval) {
        if (Math.abs(fileInterval) > CONTINUITY_BOUNDARY || Math.abs(rankInterval) > CONTINUITY_BOUNDARY) {
            return CONTINUOUS;
        }
        return DISCONTINUOUS;
    }
}
