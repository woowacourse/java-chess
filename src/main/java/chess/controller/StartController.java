package chess.controller;

import chess.service.ChessGameService;
import chess.view.OutputView;

public class StartController implements Controller {

    @Override
    public void execute(ChessGameService chessGameService, OutputView outputView) {
        chessGameService.start();
        outputView.printBoard(chessGameService.loadBoard());
    }
}
