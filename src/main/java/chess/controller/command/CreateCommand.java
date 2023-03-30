package chess.controller.command;

import chess.controller.GameController;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class CreateCommand implements GameController {

    public static CreateCommand of(List<String> commandLine) {
        return new CreateCommand();
    }

    @Override
    public void execute(final ChessGameService chessGameService, OutputView outputView) {
        chessGameService.create();
        outputView.printBoard(chessGameService.findChessBoard());
    }
}
