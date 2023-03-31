package chess.controller.command;

import chess.controller.GameController;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class EndCommand implements GameController {

    public static EndCommand of(List<String> commandLine) {
        return new EndCommand();
    }

    @Override
    public void execute(final ChessGameService chessGameService, OutputView outputView) {
        chessGameService.end();
    }
}
