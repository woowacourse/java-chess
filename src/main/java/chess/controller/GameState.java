package chess.controller;

public enum GameState {
    PLAYING, END;

    public boolean isPlaying() {
        return this == PLAYING;
    }
}
