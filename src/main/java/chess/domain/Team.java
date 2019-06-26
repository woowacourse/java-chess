package chess.domain;

public enum Team {
    BLACK, WHITE, NEUTRAL;

    public boolean isEmpty() {
        return this == Team.NEUTRAL;
    }

    public boolean isEnemy(Team other) {
        return (other == Team.BLACK && this == Team.WHITE) ||
            (other == Team.WHITE && this == Team.BLACK);
    }

    public boolean isAlly(Team team) {
        return this == team;
    }
}
