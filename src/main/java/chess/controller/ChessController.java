package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandActions;
import chess.controller.command.CommandType;
import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.exception.StartCommandException;
import chess.dto.ChessGameDto;
import chess.dto.SquareMoveDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {

    private final CommandActions commandActions;
    private final ChessGameDao chessGameDao;
    private ChessGame chessGame;

    public ChessController(final ChessGameDao chessGameDao) {
        this.commandActions = initCommandActions();
        this.chessGameDao = chessGameDao;
        this.chessGame = new ChessGame();
    }

    private CommandActions initCommandActions() {
        return new CommandActions(Map.of(
                CommandType.START, this::restart,
                CommandType.END, this::end,
                CommandType.MOVE, this::move,
                CommandType.STATUS, this::status,
                CommandType.LOAD, this::load,
                CommandType.SAVE, this::save
        ));
    }

    public void run() {
        OutputView.printStartMessage();
        start();
        Command command = Command.start();
        while (isRunning(command)) {
            command = readCommand();
            commandActions.execute(command);
        }
    }

    private void start() {
        try {
            final Command startCommand = readCommand();
            checkStartCommand(startCommand);
            OutputView.printGameStatus(chessGame.getGameStatus());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            start();
        }
    }

    private Command readCommand() {
        try {
            final List<String> input = InputView.readCommand();
            return Command.from(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand();
        }
    }

    private void checkStartCommand(final Command command) {
        if (!command.isStartCommand()) {
            throw new StartCommandException();
        }
    }

    private boolean isRunning(final Command command) {
        return !command.isEndCommand() && !chessGame.isEnd();
    }

    private void restart(final Command command) {
        chessGame.restart();
        OutputView.printGameStatus(chessGame.getGameStatus());
    }

    private void end(final Command command) {
    }

    private void move(final Command command) {
        try {
            SquareMoveDto moveDto = SquareMoveDto.from(command.getMoveSource(), command.getMoveDestination());
            chessGame.move(moveDto);
            OutputView.printGameStatus(chessGame.getGameStatus());
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void status(final Command command) {
        OutputView.printScore(chessGame.getScore());
        OutputView.printGameStatus(chessGame.getGameStatus());
    }

    private void load(final Command command) {
        try {
            final int chessGameId = command.getLoadId();
            chessGame = chessGameDao.findById(chessGameId);
            OutputView.printGameStatus(chessGame.getGameStatus());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void save(final Command command) {
        final int chessGameId = chessGameDao.save(ChessGameDto.of(chessGame));
        OutputView.printGameStatus(chessGame.getGameStatus());
        OutputView.printSavedId(chessGameId);
    }
}
