package chess.domain;

public enum Team {
    BLACK, WHITE, NEUTRAL;

    boolean isEmpty() {
        return this == Team.NEUTRAL;
    }

    boolean isEnemy(Team other) {
        return (other == Team.BLACK && this == Team.WHITE) ||
            (other == Team.WHITE && this == Team.BLACK);
    }

    boolean isAlly(Team team) {
        return this == team;
    }
}
