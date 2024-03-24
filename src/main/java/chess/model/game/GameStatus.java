package chess.model.game;

public class GameStatus {

    private Status status;

    public GameStatus() {
        status = Status.READY;
    }

    public GameStatus(Status status) {
        this.status = status;
    }

    public void changeStart() {
        if (status.isReady()) {
            status = Status.START;
            return;
        }
        throw new UnsupportedOperationException("게임이 이미 진행 중 입니다.");
    }

    public void changeMove() {
        if (status.isMove()) {
            return;
        }
        if (status.isStart()) {
            status = Status.MOVE;
            return;
        }
        throw new UnsupportedOperationException("게임을 start 해 주세요.");
    }

    public void changeEnd() {
        status = Status.END;
    }

    public boolean isRunning() {
        return status.isRunning();
    }

    public boolean isStarted() {
        return status.isStart();
    }

    public boolean isMoved() {
        return status.isMove();
    }

    public boolean isEnded() {
        return status.isEnd();
    }
}
