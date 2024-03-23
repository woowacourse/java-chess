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
        if (cmd == Command.START && cmd.isAvailableSize(command.size())) {
            return new Running();
        }
        if (cmd == Command.END && cmd.isAvailableSize(command.size())) {
            return new End();
        }
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }
}
