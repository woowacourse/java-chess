package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.printStartMessage();
        if (InputView.inputCommand()) {
            Board board = new Board();
            OutputView.printChessBoard(board.mapPositionToCharacter());
            InputView.inputEndCommand();
        }
    }
}
