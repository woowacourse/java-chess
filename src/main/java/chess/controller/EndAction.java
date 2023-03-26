package chess.controller;

import chess.dto.ChessRequest;
import chess.service.ChessGameService;
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
