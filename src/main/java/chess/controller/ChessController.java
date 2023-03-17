package chess.controller;

import chess.domain.Board;
import chess.view.OutputView;

public class ChessController {

    private final Board board = Board.create();

    public void run() {
        OutputView.printStartMessage();
    }
}
