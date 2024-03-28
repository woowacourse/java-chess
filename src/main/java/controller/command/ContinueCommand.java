package controller.command;

import controller.ChessController;
import view.format.command.PlayCommandFormat;

import java.sql.SQLException;

public class ContinueCommand implements Command {

    private final ChessController controller;

    public ContinueCommand(final ChessController controller) {
        this.controller = controller;
    }

    @Override
    public void executeStart() throws SQLException {
        controller.continueGame();
    }

    @Override
    public void executePlay(final PlayCommandFormat command, final int gameId) {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }
}
