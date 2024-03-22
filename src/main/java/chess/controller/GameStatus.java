package chess.controller;

public interface GameStatus {
    boolean isPlayable();

    GameStatus play();
}
