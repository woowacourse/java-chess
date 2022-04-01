package chess.model.command;

import chess.model.position.Position;

public interface State {
    Command turnState(String input);

    Command turnFinalState(String input);

    boolean isEnd();

    boolean isMove();

    boolean isStatus();

    Position getSourcePosition();

    Position getTargetPosition();
}
