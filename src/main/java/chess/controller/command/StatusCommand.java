package chess.controller.command;

import chess.domain.GameRoom;
import chess.domain.Team;
import chess.view.OutputView;
import java.util.Map;

public class StatusCommand implements Command {

    public StatusCommand() {
    }

    @Override
    public void execute(final GameRoom gameRoom) {
        final Map<Team, Double> scores = Map.of(
                Team.WHITE, gameRoom.calculateScore(Team.WHITE),
                Team.BLACK, gameRoom.calculateScore(Team.BLACK));
        OutputView.printScore(scores);
    }
}
