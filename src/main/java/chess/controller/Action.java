package chess.controller;

import chess.dto.ChessRequest;
import chess.service.ChessGameService;
import chess.view.OutputView;

@FunctionalInterface
public interface Action {
    GameCommand execute(ChessGameService chessGameService, OutputView outputView, ChessRequest chessRequest);
}
