package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class ChessGameController {

    private final OutputView outputView;
    private final ChessGame chessGame;

    public ChessGameController(final OutputView outputView, final ChessGame chessGame) {
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printBoard(chessGame.getBoard());
    }
}
