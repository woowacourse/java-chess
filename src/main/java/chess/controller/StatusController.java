package chess.controller;

import chess.service.ChessGameService;
import chess.view.OutputView;

public class StatusController implements Controller {

    @Override
    public void execute(ChessGameService chessGameService, OutputView outputView) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
        outputView.printStatus(chessGameService.getGameResult());
    }
}
