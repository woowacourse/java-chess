package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.StartingStatus;
import domain.ChessGameResult;
import service.ChessGameService;
import service.ChessResultService;
import view.OutputView;

import java.util.List;

public class EndCommand implements Command {

    private final ChessGameService chessGameService;
    private final ChessResultService chessResultService;

    public EndCommand(final ChessGameService chessGameService, final ChessResultService chessResultService) {
        this.chessGameService = chessGameService;
        this.chessResultService = chessResultService;
    }

    @Override
    public ChessProgramStatus executeStart() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public ChessProgramStatus executePlay(final List<String> playCommandFormat, final int gameId) {
        chessGameService.endGame(gameId);
        chessResultService.saveResult(gameId);

        final ChessGameResult chessGameResult = chessResultService.calculateResult(gameId);
        OutputView.printStatus(chessGameResult);

        return new StartingStatus();
    }
}
