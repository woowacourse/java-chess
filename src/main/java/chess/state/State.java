package chess.state;

public interface State {

    State start();

    State end();

    State move(final String[] commands);

    boolean isNotEnded();
}
