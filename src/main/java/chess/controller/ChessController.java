package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.InputOption;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        Board board = null;
        InputOption option = InputView.inputStartOrEnd();
        if (option == InputOption.START) {
            board = initializeBoard();
            if (InputView.inputOption() == InputOption.END) {
                return;
            }
        }
        if (option == InputOption.END) {
            return;
        }
    }

    private Board initializeBoard() {
        Board board = new Board(BoardFactory.getInitialPieces());
        OutputView.printInitialChessBoard(board);
        return board;
    }
}

