package chess.console.state;

public interface State {
    boolean isEnd();

    State run(String[] inputs);
}
