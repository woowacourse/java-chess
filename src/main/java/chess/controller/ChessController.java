package chess.controller;

import chess.controller.command.GameCommand;
import chess.controller.command.RoomCommand;
import chess.domain.game.ChessGame;
import chess.repository.dao.JdbcChessGameDao;
import chess.repository.dao.JdbcPieceDao;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final int MAIN_COMMAND_INDEX = 0;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessService = new ChessService(new JdbcChessGameDao(), new JdbcPieceDao());
    }

    public void run() {
        outputView.printExistChessGameId(chessService.findAllChessGame());

        final ChessGame chessGame = startChessGame();

        outputView.printStartMessage();

        while (chessService.isRunnable(chessGame)) {
            play(chessGame);
        }
    }

    private ChessGame startChessGame() {
        try {
            final List<String> roomCommand = inputView.readRoomCommand();
            final RoomCommand mainRoomCommand = RoomCommand.from(roomCommand.get(MAIN_COMMAND_INDEX));
            return mainRoomCommand.execute(chessService, roomCommand);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return startChessGame();
        }
    }

    private void play(final ChessGame chessGame) {
        try {
            final List<String> gameCommand = inputView.readGameCommand();
            final GameCommand mainGameCommand = GameCommand.from(gameCommand.get(MAIN_COMMAND_INDEX));
            mainGameCommand.execute(chessService, chessGame, gameCommand, outputView);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            play(chessGame);
        }
    }
}
