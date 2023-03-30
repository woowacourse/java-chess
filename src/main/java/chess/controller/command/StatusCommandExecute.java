package chess.controller.command;

import chess.domain.chessGame.ChessGame;
import chess.view.OutputView;

import java.util.List;

public class StatusCommandExecute implements CommandExecute {
    private final ChessGame chessGame;

    public StatusCommandExecute(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame execute(String ignored1, String ignored2) {
        List<Double> score = chessGame.calculateScore();
        OutputView.getInstance().printStatus(score);
        return chessGame;
    }
}
