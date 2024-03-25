package chess.game.status;

public interface GameStatus {
    boolean isPlayable();

    GameStatus play();
}
