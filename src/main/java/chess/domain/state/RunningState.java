package chess.domain.state;

public class RunningState implements GameState {

    public static final GameState STATE = new RunningState();

    public void startGame(Runnable runnable) {
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void enterLoad(Runnable runnable){
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void loadGame(Runnable runnable){
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void cancelLoad(Runnable runnable){
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void movePiece(Runnable runnable) {
        runnable.run();
    }

    public void displayGameStatus(Runnable runnable) {
        runnable.run();
    }

    public void finishGame(Runnable runnable) {
        runnable.run();
    }

    public boolean isRunning() {
        return true;
    }

    public boolean isFinished() {
        return false;
    }
}
