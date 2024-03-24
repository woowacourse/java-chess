package model.status;

import constant.ErrorCode;
import exception.InvalidStatusException;
import java.util.List;
import model.ChessBoard;
import model.command.CommandLine;
import model.position.Moving;
import model.position.Position;

public class Running implements GameStatus {

    @Override
    public GameStatus play(final CommandLine commandLine, final ChessBoard chessBoard) {
        if (commandLine.isEnd()) {
            return new End();
        }
        if (commandLine.isMove()) {
            final Moving moving = convert(commandLine.getBody());
            chessBoard.move(moving);
            return new Running();
        }
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }

    private Moving convert(final List<String> command) {
        return new Moving(Position.from(command.get(CommandLine.CURRENT_POSITION_INDEX)),
                Position.from(command.get(CommandLine.NEXT_POSITION_INDEX)));
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
