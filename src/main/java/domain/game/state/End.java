package domain.game.state;

public class End implements State {
    @Override
    public State start() {
        return new Start();
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임이 이미 종료되어 있습니다.");
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isEnded() {
        return true;
    }

    @Override
    public boolean isNotEnded() {
        return false;
    }
}
