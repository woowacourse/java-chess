package controller.command;

import domain.board.ChessBoard;
import view.OutputView;

public interface Command {
    void execute(ChessBoard board, OutputView outputView);

    boolean isNotEnded();
}
