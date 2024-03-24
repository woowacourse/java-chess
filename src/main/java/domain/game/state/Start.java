package domain.game.state;

public class Start implements State {
    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 시작되어 있습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }
}
