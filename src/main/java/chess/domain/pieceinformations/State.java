package chess.domain.pieceinformations;

public enum State {
    ALIVE, DEAD;

    public static State getInstance(boolean value) {
        if (value) {
            return ALIVE;
        }
        return DEAD;
    }
}
