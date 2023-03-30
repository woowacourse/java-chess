package chess.controller.command.execute;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class EndCommand implements ExecuteCommand {

    @Override
    public void execute(
            final ChessGame chessGame,
            final ChessGameService chessGameService,
            final OutputView outputView
    ) {
        chessGame.end();
        chessGameService.updateChessGameStateAndTurn(chessGame);
    }
}
