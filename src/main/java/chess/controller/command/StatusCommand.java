package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.Result;
import chess.view.OutputView;

import java.util.List;

public class StatusCommand implements Command {

    @Override
    public void execute(ChessGame chessGame) {
        List<Result> results = chessGame.calculateResults();
        OutputView.printResults(results);
    }
}
