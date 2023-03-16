package chess;

import chess.controller.ChessController;
import chess.domain.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        new ChessController().start();
    }
}
