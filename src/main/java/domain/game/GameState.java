package domain.game;

public enum GameState {
    RUN("run"),
    END("end"),
    KING_DEAD("king_dead");

    private final String stateText;

    GameState(String stateText) {
        this.stateText = stateText;
    }
}
