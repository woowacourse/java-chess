package chess.controller;

import chess.service.ChessGameService;
import chess.view.OutputView;

public interface GameController {

    public void execute(ChessGameService chessGameService, OutputView outputView);

}
