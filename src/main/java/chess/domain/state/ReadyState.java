package chess.domain.state;

public class ReadyState implements GameState {

    public static final GameState STATE = new ReadyState();

    public void startGame(Runnable runnable) {
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

    public boolean isFinished() {
        return false;
    }
}
