package chess.domain.state;

public class Running implements State {
    @Override
    public State next() {
        return new End();
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
        return false;
    }
}
