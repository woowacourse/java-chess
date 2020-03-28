package chess.controller;

import chess.domain.Run;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    public void run() {
        Run runner = InputView.inputStart();
        if (runner.isStart()) {
            Board board = BoardFactory.createBoard();
            OutputView.printInitializedBoard(board);
        }
    }
}
