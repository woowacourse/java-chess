package controller.status;

import domain.Team;
import domain.player.Player;
import view.InputView;

public class RunningStatus implements ChessProgramStatus {

    private final int gameId;
    private final Player currentPlayer;
    private final Team currentTeam;

    public RunningStatus(final int gameId, final Player currentPlayer, final Team currentTeam) {
        this.gameId = gameId;
        this.currentPlayer = currentPlayer;
        this.currentTeam = currentTeam;
    }

    @Override
    public String readCommand() {
        return InputView.readGameCommand(currentTeam, currentPlayer.getName());
    }

    @Override
    public int gameId() {
        return gameId;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public boolean isStarting() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
