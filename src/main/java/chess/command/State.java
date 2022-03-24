package chess.command;

public interface State {
    State turnState(String input);
    boolean isEnd();
}
