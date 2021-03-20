package chess.service.state;

import chess.service.ChessService;

public class End extends Finished {
    @Override
    public GameState run(ChessService chessService) {
        return new End();
    }
}
