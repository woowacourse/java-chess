package chess.domain.state;

public class Exit implements State {
    @Override
    public State exit() {
        throw new IllegalArgumentException("[ERROR] 이미 종료된 상태입니다.");
    }

    @Override
    public State init() {
        throw new IllegalArgumentException("[ERROR] 이미 종료된 상태입니다.");
    }

    @Override
    public State next() {
        throw new IllegalArgumentException("[ERROR] 이미 종료된 상태입니다.");
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
