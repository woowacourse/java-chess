package chess.service.state;

import chess.service.ChessService;

public class Ready implements GameState {
    @Override
    public GameState run(ChessService chessService) {
        return new Playing();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
