package chess.service.state;

import chess.service.ChessService;

public class Ready implements GameState {
    @Override
    public GameState playRound(ChessService chessService) {
        chessService.setGameOver(true);
        return new Playing();
    }
}
