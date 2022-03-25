package chess.state;

public interface State {
    State start();

    State move(String input);

    State status();

    State end();
}
