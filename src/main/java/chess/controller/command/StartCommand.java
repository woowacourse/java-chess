package chess.controller.command;

import chess.controller.GameController;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class StartCommand implements GameController {

    public static StartCommand of(List<String> commandLine) {
        return new StartCommand();
    }

    @Override
    public void execute(final ChessGameService chessGameService, OutputView outputView) {
        chessGameService.start();
        outputView.printBoard(chessGameService.findChessBoard());
    }

}
