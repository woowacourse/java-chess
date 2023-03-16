package chess.domain.movement;

public enum Continuity {
    CONTINUOUS,
    DISCONTINUOUS;

    public static Continuity of(final int fileInterval, final int rankInterval) {
        if (Math.abs(fileInterval) > 1 || Math.abs(rankInterval) > 1) {
            return CONTINUOUS;
        }
        return DISCONTINUOUS;
    }
}
