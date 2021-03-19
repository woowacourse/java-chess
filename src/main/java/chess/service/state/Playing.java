package chess.service.state;

import chess.service.ChessService;
import chess.view.OutputView;

public class Playing implements GameState {
    @Override
    public void playRound(ChessService chessService) {
        OutputView.printGridStatus(chessService.grid());
    }
}
