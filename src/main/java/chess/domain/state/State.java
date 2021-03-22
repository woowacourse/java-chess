package chess.domain.state;

public interface State {
    State start();
    State end();
    State status();
    State move(boolean isKingDead);

    boolean isNotRunning();
}
