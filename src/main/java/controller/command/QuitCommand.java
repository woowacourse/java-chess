package controller.command;

import controller.ChessController;
import view.format.command.PlayCommandFormat;

public class QuitCommand implements Command {

    private final ChessController controller;

    public QuitCommand(final ChessController controller) {
        this.controller = controller;
    }

    @Override
    public void executeStart() {
        controller.quit();
    }

    @Override
    public void executePlay(final PlayCommandFormat command, final int gameId) {
        controller.quit();
    }
}
