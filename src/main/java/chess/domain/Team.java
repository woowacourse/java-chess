package chess.domain;

public enum Team {
    BLACK,
    WHITE;

    public static Team getAnotherTeam(final Team userTeam) {
        if (Team.BLACK.equals(userTeam)) {
            return WHITE;
        }
        return BLACK;
    }
}
