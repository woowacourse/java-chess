package chess.domain.chesspiece;

public enum Team {
    BLACK(1),
    WHITE(-1),
    BLANK(0);

    private int direction;

    Team(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
