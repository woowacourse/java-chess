package chess.controller;

import chess.service.ChessGameService;
import chess.view.OutputView;

public interface Controller {
    void execute(ChessGameService chessGameService, OutputView outputView);
}
