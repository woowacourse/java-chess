package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.util.Run;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private Run runner;
    private Board board;

    public void run() {
        OutputView.printInputStartGuideMessage();
        String[] inputCommand = InputView.inputCommand();
        runner = Run.of(inputCommand[0]);

        while(runner.isNotEnd()) {
            if (runner.isStart()) {
                board = BoardFactory.createBoard();
            }
            if (runner.isMove()) {
                board = board.movePiece(inputCommand[1], inputCommand[2]);
            }
            OutputView.printBoard(board);
            inputCommand = InputView.inputCommand();
            runner = Run.of(inputCommand[0]);
        }
    }
}
