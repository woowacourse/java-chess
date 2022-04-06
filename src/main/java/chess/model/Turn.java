package chess.model;

public class Turn {
    private static final Turn TURN_BLACK = new Turn(Team.BLACK);
    private static final Turn TURN_WHITE = new Turn(Team.WHITE);
    private static final Turn TURN_NONE = new Turn(Team.NONE);
    private final Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public static Turn init() {
        return TURN_WHITE;
    }

    public boolean isCurrentTeam(Team team) {
        return this.team.equals(team);
    }

    public Turn change() {
        if (team.equals(Team.BLACK)) {
            return TURN_WHITE;
        }
        return TURN_BLACK;
    }

    public String getThisTurn() {
        return team.name();
    }

    public Turn finish() {
        return TURN_NONE;
    }
}
