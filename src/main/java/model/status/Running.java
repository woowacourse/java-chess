package model.status;

import constant.ErrorCode;
import exception.InvalidStatusException;
import java.util.List;
import model.ChessBoard;
import model.Command;
import model.position.Moving;
import model.position.Position;

public class Running implements GameStatus {

    @Override
    public GameStatus play(final List<String> command, final ChessBoard chessBoard) {
        final Command cmd = Command.from(command.get(Command.HEAD_INDEX));
        if (cmd == Command.END && cmd.isAvailableSize(command.size())) {
            return new End();
        }
        if (cmd == Command.MOVE && cmd.isAvailableSize(command.size())) {
            final Moving moving = convert(command);
            chessBoard.move(moving);
            return new Running();
        }
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }

    private Moving convert(final List<String> command) {
        return new Moving(Position.from(command.get(Command.CURRENT_INDEX)),
                Position.from(command.get(Command.NEXT_INDEX)));
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
