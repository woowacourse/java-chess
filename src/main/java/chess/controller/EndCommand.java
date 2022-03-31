package chess.controller;

import chess.domain.game.state.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command{
    @Override
    public ChessGame run(ChessGame chessGame) {
        try {
            return chessGame.end();
        } catch (IllegalStateException exception) {
            OutputView.printError(exception.getMessage());
        }
        return chessGame;
    }
}
