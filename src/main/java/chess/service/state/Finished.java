package chess.service.state;

import chess.service.ChessService;

public class Finished implements GameState {
    @Override
    public void playRound(ChessService chessService) {
        return;
    }
}
