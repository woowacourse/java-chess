package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.RunningStatus;
import domain.ChessGameResult;
import domain.Team;
import domain.player.Player;
import service.ChessGameService;
import service.ChessResultService;
import view.OutputView;

import java.util.List;

public class StatusCommand implements Command {

    private final ChessResultService chessResultService;
    private final ChessGameService chessGameService;

    public StatusCommand(final ChessResultService chessResultService, final ChessGameService chessGameService) {
        this.chessResultService = chessResultService;
        this.chessGameService = chessGameService;
    }

    @Override
    public ChessProgramStatus executeStart() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public ChessProgramStatus executePlay(final List<String> playCommandFormat, final int gameId) {
        final ChessGameResult chessGameResult = chessResultService.calculateResult(gameId);
        OutputView.printStatus(chessGameResult);

        final Team currentTeam = chessGameService.findCurrentTeam(gameId);
        final Player currentPlayer = chessGameService.findPlayer(gameId, currentTeam);

        return new RunningStatus(gameId, currentPlayer, currentTeam);
    }
}
