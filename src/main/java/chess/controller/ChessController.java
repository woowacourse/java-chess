package chess.controller;

import chess.domain.Board;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;

    public ChessController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void init() {
        Board board = new Board();
        outputView.printBoard(board.getPiecePosition());
    }
}
