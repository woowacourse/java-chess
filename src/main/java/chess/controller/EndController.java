package chess.controller;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class EndController implements Controller {
    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        chessGame.end();
    }
}
