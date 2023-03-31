package chess.controller.command;

import chess.controller.GameController;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class StatusCommand implements GameController {

    public static StatusCommand of(List<String> commandLine) {
        return new StatusCommand();
    }

    @Override
    public void execute(final ChessGameService chessGameService, OutputView outputView) {
        outputView.printStatus(chessGameService.findStatus());
    }
}
