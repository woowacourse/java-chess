package chess.model;

public class Turn {
    private static final Turn TURN_BLACK = new Turn(Team.BLACK);
    private static final Turn TURN_WHITE = new Turn(Team.WHITE);
    private static final Turn TURN_NONE = new Turn(Team.NONE);
    private static final String BLACK = "black";
    private static final String WHITE = "white";

    private final Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public static Turn init() {
        return TURN_WHITE;
    }

    public static Turn from(String team) {
        if (BLACK.equalsIgnoreCase(team)) {
            return TURN_BLACK;
        } else if (WHITE.equalsIgnoreCase(team)) {
            return TURN_WHITE;
        }
        return TURN_NONE;
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

    public String finish() {
        return TURN_NONE.getThisTurn();
    }
}
