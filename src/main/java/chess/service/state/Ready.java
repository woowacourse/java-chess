package chess.service.state;

import chess.service.ChessService;

public class Ready implements GameState {
    @Override
    public void playRound(ChessService chessService) {
        chessService.setGameOver(true);
    }
}
