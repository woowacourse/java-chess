package chess.controller;

import chess.domain.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        String command = InputView.requestCommand();
        while(command.equals("start")){
            ChessBoard chessBoard = new ChessBoard();
            OutputView.printChessBoard(chessBoard);
            command = InputView.requestCommand();
        }
    }
}
