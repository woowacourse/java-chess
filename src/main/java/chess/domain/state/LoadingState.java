package chess.domain.state;

public class LoadingState implements GameState {

    public static final GameState STATE = new LoadingState();

    public void startGame(Runnable runnable) {
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void enterLoad(Runnable runnable) {
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void loadGame(Runnable runnable) {
        runnable.run();
    }

    public void cancelLoad(Runnable runnable) {
        runnable.run();
    }

    public void movePiece(Runnable runnable) {
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void displayGameStatus(Runnable runnable) {
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void finishGame(Runnable runnable) {
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public boolean isRunning() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }
}
