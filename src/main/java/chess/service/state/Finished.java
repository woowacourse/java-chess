package chess.service.state;

import chess.service.ChessService;

public class Finished implements GameState {
    @Override
    public GameState playRound(ChessService chessService) {
        return new Finished();
    }
}
