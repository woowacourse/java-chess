package controller.command;

import controller.status.ChessProgramStatus;
import controller.status.StartingStatus;
import domain.player.Player;
import dto.PlayerGameRecordDto;
import service.ChessResultService;
import service.PlayerService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class RecordCommand implements Command {

    private final PlayerService playerService;
    private final ChessResultService chessResultService;

    public RecordCommand(final PlayerService playerService, final ChessResultService chessResultService) {
        this.playerService = playerService;
        this.chessResultService = chessResultService;
    }

    @Override
    public ChessProgramStatus executeStart() {
        final Player player = readPlayer();
        final PlayerGameRecordDto gameRecord = chessResultService.findGameRecord(player);
        OutputView.printGameRecord(gameRecord);

        return new StartingStatus();
    }

    @Override
    public ChessProgramStatus executePlay(final List<String> command, final int gameId) {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    private Player readPlayer() {
        while (true) {
            try {
                final String name = InputView.readPlayerName();
                return playerService.findPlayer(name);
            } catch (final IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
