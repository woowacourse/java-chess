package chess.controller;

public enum GameState {
    PREPARING, PLAYING, END;

    public boolean isPlayable() {
        return this != END;
    }
}
