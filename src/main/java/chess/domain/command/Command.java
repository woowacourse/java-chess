package chess.domain.command;

import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public interface Command {
    String MOVE_COMMAND = "move";
    String END_COMMAND = "end";
    String STATUS_COMMAND = "status";
    String START_COMMAND = "start";

    static Command from(String command) {
        if (command.startsWith(MOVE_COMMAND)) {
            return new Move(command);
        }
        if (command.equals(END_COMMAND)) {
            return new End();
        }
        if (command.equals(STATUS_COMMAND)) {
            return new Status();
        }
        if (command.equals(START_COMMAND)) {
            return new Start();
        }

        throw new InvalidCommandException();
    }

    boolean isStart();

    boolean isMove();

    Position source();

    Position target();

    boolean isEnd();

    boolean isStatus();
}
