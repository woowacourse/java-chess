package chess.domain.state;

public interface State {
    boolean isReady();

    boolean isFinish();

    State start();

    State end();
}
