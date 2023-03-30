package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command {
    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        chessGame.end();
    }
}
