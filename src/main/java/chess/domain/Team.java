package chess.domain;

public enum Team {
    BLACK,
    WHITE;

    public static Team enemyTeam(final Team userTeam) {
        if (Team.BLACK.equals(userTeam)) {
            return WHITE;
        }
        return BLACK;
    }
}
