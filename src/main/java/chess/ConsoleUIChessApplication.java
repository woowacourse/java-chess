package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.printInitialMessage();
        ChessController.start(InputView.inputStartOrEnd());

        Board board = BoardFactory.create();
        OutputView.printBoard(board.getBoard());

        while (true) {
            ChessController.playTurn(InputView.inputMoveOrStatus(), board);
        }
    }
}
