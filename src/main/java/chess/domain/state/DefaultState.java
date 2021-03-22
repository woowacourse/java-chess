package chess.domain.state;

public abstract class DefaultState implements State {
    public DefaultState() {}

    @Override
    abstract public State start();

    @Override
    abstract public State end();

    @Override
    abstract public State status();

    @Override
    abstract public State move(boolean isKingDead);

    @Override
    abstract public boolean isNotRunning();
}
