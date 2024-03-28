package controller.command;

import controller.ChessController;
import view.format.command.PlayCommandFormat;

public class RecordCommand implements Command {

    private final ChessController controller;

    public RecordCommand(final ChessController controller) {
        this.controller = controller;
    }

    @Override
    public void executeStart() {
        controller.printPlayerRecord();
    }

    @Override
    public void executePlay(final PlayCommandFormat command, final int gameId) {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }
}
