package chess.model.position;

public enum RankDirection {
    UP(1),
    DOWN(-1);

    private final int value;

    RankDirection(int value) {
        this.value = value;
    }

    public boolean match(Movement movement) {
        int movementRankDirection = Integer.signum(movement.getRankGap());
        return value == movementRankDirection;
    }
}
