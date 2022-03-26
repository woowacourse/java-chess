package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        runByCommend();
    }

    private void runByCommend() {
        while (true) {
            String commend = InputView.inputCommend();
            if (commend.equals("end")) {
                break;
            }
            ChessGame chessGame = new ChessGame();
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }
}
