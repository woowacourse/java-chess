package chess.domain.command;

import chess.domain.position.Position;

public interface Command {

    boolean isStart();

    boolean isMove();

    boolean isStatus();

    Position getFromPosition();

    Position getToPosition();
}
