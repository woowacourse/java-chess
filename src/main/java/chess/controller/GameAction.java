package chess.controller;

import chess.dto.ChessRequest;
import chess.service.ChessGameService;

@FunctionalInterface
public interface GameAction {
    GameCommand execute(ChessGameService chessGameService, ChessRequest chessRequest);

}
