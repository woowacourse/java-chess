package chess.controller;

import chess.domain.ChessBoard;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    public static void main(String[] args) {
        OutputView.printInitialGuide();

        if (Command.of(InputView.inputCommand()) == Command.START) {
            ChessBoard chessBoard = new ChessBoard();
            OutputView.printChessBoard(chessBoard.getChessBoard());
        }

        if (Command.of(InputView.inputCommand()) == Command.END) {
            return;
        }
    }
}
