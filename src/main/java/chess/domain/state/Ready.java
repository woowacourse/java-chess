package chess.domain.state;

public class Ready implements State {
    @Override
    public State next() {
        return new Running();
    }

    @Override
    public State init() {
        return new Running();
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State exit() {
        return new Exit();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
