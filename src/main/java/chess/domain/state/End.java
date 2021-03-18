package chess.domain.state;

public class End implements State {
    @Override
    public State next() {
        throw new IllegalArgumentException("[ERROR] 종료된 상태입니다.");
    }

    @Override
    public State exit() {
        return new Exit();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public State init() {
        return new Running();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
