package controller.command;

import domain.board.ChessBoard;
import view.OutputView;

public class StartOnCommand implements Command {
    @Override
    public void execute(final ChessBoard board, final OutputView outputView) {
        outputView.printBoard(board);
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }
}
