package chess.domain.command;

import static chess.view.Command.*;

public class CommandFactory {
    private CommandFactory() {
    }

    public static Command initialCommand(final String command) {
        if (START.isSame(command)) {
            return new Start();
        }
        if (END.isSame(command)) {
            return new End();
        }
        throw new UnsupportedOperationException("start 또는 end를 입력해주세요!");
    }

    public static Command runningCommand(final String command) {
        if (MOVE.isSame(command)) {
            return new Move();
        }
        if (END.isSame(command)) {
            return new End();
        }
        throw new UnsupportedOperationException("move 또는 end를 입력해주세요!");
    }
}
