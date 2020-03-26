package chess;

import chess.controller.ChessController;
import chess.domain.board.BoardFactory;
import chess.domain.board.Boards;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.printInitialMessage();
        ChessController.start(InputView.inputStartOrEnd());

        Boards boards = BoardFactory.create();
        OutputView.printBoard(boards.getTotal());

        while (true) {
            ChessController.playTurn(InputView.inputMoveOrStatus(), boards);
        }
    }
}
