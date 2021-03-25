package chess.domain.state;

public class Running implements State {
    public Running() {
        super();
    }

    @Override
    public State start() {
        return new Running();
    }

    @Override
    public State end() {
        return new GameEnd();
    }

    @Override
    public State status() {
        return this;
    }

    @Override
    public State move(boolean isKingDead) {
        if (isKingDead) {
            return new GameEnd();
        }
        return this;
    }

    @Override
    public boolean isNotRunning() {
        return false;
    }
}
