package chess.service.state;

import chess.service.ChessService;

public interface GameState {
    public GameState playRound(ChessService chessService);
}
