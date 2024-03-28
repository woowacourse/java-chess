package controller.command;

import controller.ChessController;
import view.format.command.PlayCommandFormat;

public class StatusCommand implements Command {

    private final ChessController controller;

    public StatusCommand(final ChessController controller) {
        this.controller = controller;
    }

    @Override
    public void executeStart() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public void executePlay(final PlayCommandFormat playCommandFormat, final int gameId) {
        controller.runStatus(gameId);
    }
}
