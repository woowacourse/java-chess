package model.status;

import constant.ErrorCode;
import exception.InvalidStatusException;
import model.command.CommandLine;

public class Initialization {

    private Initialization() {
    }

    public static GameStatus gameSetting(CommandLine commandLine) {
        if (commandLine.isStart()) {
            return new Running();
        }
        if (commandLine.isEnd()) {
            return new End();
        }
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }
}
