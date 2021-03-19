package chess.service.state;

import chess.service.ChessService;

public class Ready implements GameState {
    @Override
    public GameState playRound(ChessService chessService) {
        return new Playing();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
