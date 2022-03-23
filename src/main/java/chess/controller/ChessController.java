package chess.controller;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        while (true) {
            String commend = InputView.inputCommend();
            if (commend.equals("end")) {
                break;
            }
            Board board = new Board();
            OutputView.printChessBoard(board);
        }
    }
}
