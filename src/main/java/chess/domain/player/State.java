package chess.domain.player;

public enum State {
    DEAD, LiVE;

    public boolean isDead() {
        return this.equals(DEAD);
    }
}
