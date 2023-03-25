package chess.domain.state;

public interface GameState {

    void startGame(Runnable runnable);

    void enterLoad(Runnable runnable);

    void loadGame(Runnable runnable);

    void cancelLoad(Runnable runnable);

    void movePiece(Runnable runnable);

    void displayGameStatus(Runnable runnable);

    void finishGame(Runnable runnable);

    boolean isFinished();
}
