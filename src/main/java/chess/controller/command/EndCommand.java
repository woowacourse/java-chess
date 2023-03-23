package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class EndCommand implements ExecuteCommand {

    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        chessGame.end();
        outputView.printEndMessage();
    }
}
