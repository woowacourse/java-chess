package chess.domain;

public enum Team {
    BLACK, WHITE;

    public static boolean isSameTeam(Team one, Team other) {
        return one == other;
    }

    public Team enemy() {
        return isSameTeam(this, BLACK) ? WHITE : BLACK;
    }
}
