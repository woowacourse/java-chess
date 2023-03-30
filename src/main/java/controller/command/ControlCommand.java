package controller.command;

import domain.ChessGame;
import view.CommandType;

public interface ControlCommand {

    void run();

    static ControlCommand of(final CommandType type, final ChessGame chessGame) {
        if (type == CommandType.START) {
            return new StartCommand(chessGame);
        }
        if (type == CommandType.END) {
            return new EndCommand(chessGame);
        }
        throw new IllegalArgumentException("게임 진행 커맨드가 아닙니다.");
    }
}
