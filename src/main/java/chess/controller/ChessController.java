package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.GameState;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static void run() {
        OutputView.printStartGame();
        OutputView.printStartEndOption();
        while (GameState.of(InputView.inputStart()) == GameState.START) {
            ChessBoard chessBoard = new ChessBoard();
            OutputView.printChessBoard(chessBoard);
        }
    }
}
