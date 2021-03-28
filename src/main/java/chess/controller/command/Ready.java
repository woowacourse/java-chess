package chess.controller.command;

import chess.domain.board.Board;
import chess.view.OutputView;

public final class Ready extends AbstractCommand {

    public Ready(final Board board) {
        super(board);
        OutputView.printStart();
    }

    @Override
    public Command execute(final String command) {
        if ("start".equals(command)) {
            return new Play(board);
        }
        if ("exit".equals(command)) {
            return new Exit(board);
        }
        printErrorMessage("start, exit");
        return this;
    }
}
