package controller;

public enum GameStatus {
    GAME_READY,
    GAME_START,
    GAME_PLAY,
    GAME_END;

    public boolean playAble() {
        return this != GAME_END;
    }
}
