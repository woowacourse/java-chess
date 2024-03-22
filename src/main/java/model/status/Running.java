package model.status;

import constant.ErrorCode;
import exception.InvalidStatusException;
import java.util.List;
import model.Command;
import model.GameBoard;
import model.position.Moving;
import model.position.Position;

public class Running implements GameStatus {

    @Override
    public GameStatus play(final List<String> command, final GameBoard gameBoard) {
        Command cmd = Command.from(command.get(0));
        if (cmd == Command.END && command.size() == 1) {
            return new End();
        }
        if (cmd == Command.MOVE && command.size() == 3) {
            Moving moving = new Moving(Position.from(command.get(1)), Position.from(command.get(2)));
            gameBoard.move(moving);
            return new Running();
        }
        throw new InvalidStatusException(ErrorCode.INVALID_STATUS);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
