package chess.controller;

import chess.domain.PiecesPosition;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;

    public ChessController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printBoard() {
        PiecesPosition piecesPosition = new PiecesPosition();

        outputView.printChessState(piecesPosition.getPiecesPosition());
    }
}
