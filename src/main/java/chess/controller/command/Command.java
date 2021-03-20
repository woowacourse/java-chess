package chess.controller.command;

import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public interface Command {
    static Command from(String command) {
        if (command.startsWith("move")) {
            return new Move(command);
        }
        if (command.equals("end")) {
            return new End();
        }
        if (command.equals("status")) {
            return new Status();
        }
        if (command.equals("start")) {
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
