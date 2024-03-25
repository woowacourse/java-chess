package controller.command;

import domain.game.ChessGame;
import view.command.CommandType;

public class EndCommandExecutor implements CommandExecutor {
    public EndCommandExecutor(final CommandType commandType) {
        if (commandType.hasSupplements()) {
            throw new IllegalArgumentException("[ERROR]잘못된 명령어 입력입니다.");
        }
    }

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.end();
    }
}
