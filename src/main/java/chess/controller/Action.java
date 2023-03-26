package chess.controller;

import chess.service.ChessGameService;
import chess.view.ChessRequest;
import chess.view.OutputView;

@FunctionalInterface
public interface Action {
    GameCommand execute(ChessGameService chessGameService, OutputView outputView, ChessRequest chessRequest);
}
