package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.util.Run;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private Board board;

    public void run() {
        OutputView.printInputStartGuideMessage();
        String[] inputCommand = InputView.inputCommand();
        Run runner = Run.of(inputCommand[0]);
        int turnFlag = 0;

        while(runner.isNotEnd()) {
            if (runner.isStart()) {
                board = BoardFactory.createBoard();
            }
            try {
                if (runner.isMove()) {
                    board = board.movePiece(inputCommand[1], inputCommand[2], 1 - turnFlag);
                    turnFlag = 1 - turnFlag;
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            OutputView.printBoard(board);
            inputCommand = InputView.inputCommand();
            runner = Run.of(inputCommand[0]);
        }
    }
}
