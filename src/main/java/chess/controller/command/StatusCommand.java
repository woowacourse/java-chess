package chess.controller.command;

import chess.domain.Team;
import chess.service.ChessService;
import chess.service.RoomNumber;
import chess.view.OutputView;
import java.util.Map;

public class StatusCommand implements Command {

    private final RoomNumber roomNumber;

    public StatusCommand(final RoomNumber roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute(final ChessService chessService) {
        final Map<Team, Double> scores = Map.of(
                Team.WHITE, chessService.calculateScore(roomNumber, Team.WHITE),
                Team.BLACK, chessService.calculateScore(roomNumber, Team.BLACK));
        OutputView.printScore(scores);
    }
}
