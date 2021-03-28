package chess.domain.state;

public class Ready implements State {
    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State start() {
        return new Running();
    }

    @Override
    public State end() {
        return new End();
    }
}
