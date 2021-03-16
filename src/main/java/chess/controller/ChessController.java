package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void runChess() {
        OutputView.printStartGameMessage();
        while (InputView.inputCommand()) {
            Board chessBoard = new Board();
            OutputView.printCurrentBoard(chessBoard.unwrap());
        }
    }
}
