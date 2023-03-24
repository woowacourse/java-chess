package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.view.OutputView;
import java.util.Map;

public class StatusCommand implements Command {

    public StatusCommand() {
    }

    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        final Map<Team, Double> scores = Map.of(
                Team.WHITE, chessGame.calculateScore(Team.WHITE),
                Team.BLACK, chessGame.calculateScore(Team.BLACK));
        outputView.printScore(scores);
    }
}
