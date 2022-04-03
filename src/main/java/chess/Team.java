package chess;

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
        if ("black".equals(name)) {
            return Team.BLACK;
        }
        if ("white".equals(name)) {
            return Team.WHITE;
        }
        return Team.NONE;
    }

    public String value() {
        if (this.equals(Team.BLACK)) {
            return "black";
        }
        if (this.equals(Team.WHITE)) {
            return "white";
        }
        if (this.equals(Team.NONE)) {
            return "none";
        }
        throw new IllegalStateException("[ERROR] 알맞은 값이 없습니다.");
    }

    public int getForwardDirection() {
        return this.forwardDirection;
    }
}
