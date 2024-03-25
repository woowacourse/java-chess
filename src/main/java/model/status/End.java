package model.status;

import constant.ErrorCode;
import exception.InvalidStatusException;
import model.ChessGame;
import model.command.CommandLine;

public class End implements GameStatus {

    @Override
    public GameStatus play(final CommandLine commandLine, final ChessGame chessGame) {
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
