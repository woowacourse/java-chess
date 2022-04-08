package chess.board;

import java.util.Arrays;

public enum Team {
    BLACK(-1),
    WHITE(1),
    NONE(0),
    ;

    private final int forwardDirection;

    Team(int forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public static Team from(String name) {
        return Arrays.stream(values())
                .filter(team -> team.value().equals(name))
                .findFirst()
                .orElse(Team.NONE);
    }

    public String value() {
        return this.name().toLowerCase();
    }

    public boolean isOpposingTeam(Team team) {
        return this.forwardDirection + team.forwardDirection == 0;
    }

    public int getForwardDirection() {
        return this.forwardDirection;
    }
}
