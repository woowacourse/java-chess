package chess.controller;

import chess.dto.ChessRequest;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class StartAction implements Action {

    @Override
    public GameCommand execute(ChessGameService chessGameService, OutputView outputView, ChessRequest chessRequest) {
        chessGameService.start();
        outputView.printBoard(chessGameService.loadBoard());
        return GameCommand.START;
    }
}
