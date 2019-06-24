package chess.domain;

public class Turn {
    private final Team current;

    private static final Turn BLACK_TURN = new Turn(Team.BLACK);
    private static final Turn WHITE_TURN = new Turn(Team.WHITE);

    private Turn(Team current) {
        this.current = current;
    }

    public static Turn firstTurn() {
        return WHITE_TURN;
    }

    public static Turn valueOf(Team team) {
        if (team == Team.BLACK) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }

    public Turn nextTurn() {
        if (this.getCurrent() == Team.WHITE) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }

    public Team getCurrent() {
        return current;
    }
}
