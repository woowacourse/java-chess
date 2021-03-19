package chess.service.state;

import chess.service.ChessService;

public class Finished implements GameState {
    @Override
    public GameState run(ChessService chessService) {
        return new Finished();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
