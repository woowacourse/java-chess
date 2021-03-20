package chess.service.state;

import chess.service.ChessService;

public interface GameState {
    public GameState run(ChessService chessService);

    public boolean isFinished();
}
