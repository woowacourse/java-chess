package chess.controller;

import chess.domain.ChessGame;
import chess.domain.piece.CurrentPieces;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printStartMessage();
        if (chessGame.isRunning(InputView.inputStartOrEnd())) {
//            OutputView.printChessBoard();
        }
    }

}
