package chess.domain.state;

public class Running implements State{
    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State start() {
        return null;
    }
}
