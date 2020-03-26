package chess.domain.Piece.team;

public enum Team {
    WHITE(Direction.UP),
    BLACK(Direction.DOWN);

    private Direction direction;

    Team(Direction direction) {
        this.direction = direction;
    }

    public boolean isNotSame(Team team) {
        return this != team;
    }

    public int getDirection() {
        return direction.getValue();
    }
}
