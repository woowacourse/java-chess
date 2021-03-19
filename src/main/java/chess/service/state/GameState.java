package chess.service.state;

import chess.service.ChessService;

public interface GameState {
//    public boolean isFinished(String command);
//
//    public boolean isStarted(String command);

    public void playRound(ChessService chessService);
}
