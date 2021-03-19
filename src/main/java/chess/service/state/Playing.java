package chess.service.state;

import chess.service.ChessService;
import chess.view.OutputView;

public class Playing implements GameState {
    @Override
    public GameState run(ChessService chessService) {
        OutputView.printGridStatus(chessService.grid());
        return new Playing();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
