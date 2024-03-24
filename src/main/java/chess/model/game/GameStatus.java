package chess.model.game;

public class GameStatus {

    private final Status status;

    public GameStatus() {
        status = Status.READY;
    }

    public GameStatus(Status status) {
        this.status = status;
    }

    public GameStatus changeStart() {
        if (status.isReady()) {
            return new GameStatus(Status.START);
        }
        throw new UnsupportedOperationException("게임이 이미 진행 중 입니다.");
    }

    public GameStatus changeMove() {
        if (status.isMove()) {
            return this;
        }
        if (status.isStart()) {
            return new GameStatus(Status.MOVE);
        }
        throw new UnsupportedOperationException("게임을 start 해 주세요.");
    }

    public GameStatus changeEnd() {
        return new GameStatus(Status.END);
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
