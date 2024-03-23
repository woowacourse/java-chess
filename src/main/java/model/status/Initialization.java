package model.status;

import constant.ErrorCode;
import exception.InvalidStatusException;
import java.util.List;
import model.Command;

public class Initialization {

    private Initialization() {
    }

    public static GameStatus gameSetting(List<String> command) {
        Command cmd = Command.from(command.get(Command.HEAD_INDEX));
        if (cmd == Command.START && command.size() == Command.START_COMMAND_SIZE) {
            return new Running();
        }
        if (cmd == Command.END && command.size() == Command.END_COMMAND_SIZE) {
            return new End();
        }
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }
}
