package domain.game.state;

public class Init implements State {
    @Override
    public State start() {
        return new Start();
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public boolean isInit() {
        return true;
    }

    @Override
    public boolean isStarted() {
        return false;
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
