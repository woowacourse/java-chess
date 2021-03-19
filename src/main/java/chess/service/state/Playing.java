package chess.service.state;

import chess.service.ChessService;
import chess.view.OutputView;

public class Playing implements GameState {
    @Override
    public GameState playRound(ChessService chessService) {
        OutputView.printGridStatus(chessService.grid());
        return new Playing();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
