package chess.model.command;

public interface State {
    Command turnState(String input);

    Command turnFinalState(String input);

    boolean isEnd();

    boolean isMove();

    boolean isStatus();

    String getSourcePosition();

    String getTargetPosition();
}
