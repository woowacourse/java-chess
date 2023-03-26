package chess.controller;

import chess.service.ChessGameService;
import chess.view.ChessRequest;
import chess.view.OutputView;

public class EndAction implements Action {
    @Override
    public GameCommand execute(ChessGameService chessGameService, OutputView outputView, ChessRequest chessRequest) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
        outputView.printEnd();
        return GameCommand.END;
    }
}
