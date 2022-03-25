package chess.state;

public class Ended implements State {

    @Override
    public State start() {
        return new Ended();
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(String[] commands) {
        return new Ended();
    }

    @Override
    public State status() {
        return new Ended();
    }

    @Override
    public boolean isNotEnded() {
        return false;
    }
}
