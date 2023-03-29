package chess.domain.state;

public interface GameState {

    void startGame(Runnable runnable);

    void loadGame(Runnable runnable);

    void movePiece(Runnable runnable);

    void displayGameStatus(Runnable runnable);

    void finishGame(Runnable runnable);

    boolean isRunning();

    boolean isFinished();
}
