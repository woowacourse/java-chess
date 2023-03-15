package chess.controller;

import chess.domain.PiecePoints;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;

    public ChessController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printBoard() {
        PiecePoints piecePoints = new PiecePoints();

        outputView.printChessState(piecePoints.getPiecePoint());
    }
}
