package chess.controller.command;

import chess.domain.chessGame.ChessGameState;
import chess.view.OutputView;

import java.util.List;

public class StatusCommandExecute implements CommandExecute {
    private final ChessGameState chessGameState;

    public StatusCommandExecute(ChessGameState chessGameState) {
        this.chessGameState = chessGameState;
    }

    @Override
    public ChessGameState execute(String ignored1, String ignored2) {
        List<Double> score = chessGameState.calculateScore();
        OutputView.getInstance().printStatus(score);
        return chessGameState;
    }
}
