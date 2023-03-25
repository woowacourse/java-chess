package chess.controller;

import chess.service.ChessGameService;
import chess.view.OutputView;

public class ClearController implements Controller {
    @Override
    public void execute(ChessGameService chessGameService, OutputView outputView) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
        chessGameService.clear();
        outputView.printStatus(chessGameService.getGameResult());
        outputView.printClear();
        outputView.printStart();
    }
}
