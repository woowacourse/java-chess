package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class StartController implements Controller {

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        outputView.printBoard(chessGame.getBoard());
        chessGame.start();
    }
}
