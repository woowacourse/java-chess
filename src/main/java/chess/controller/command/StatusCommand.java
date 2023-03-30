package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.square.Team;
import chess.view.OutputView;
import java.util.Map;

public class StatusCommand implements Command {
    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        Map<Team, Double> status = chessGame.status();
        outputView.printStatuses(status);
    }
}
