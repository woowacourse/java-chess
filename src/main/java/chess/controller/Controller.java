package chess.controller;

import chess.dao.GameDao;
import chess.dao.MovementDao;
import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Point;
import chess.domain.point.Points;
import chess.domain.state.Start;
import chess.domain.state.State;
import chess.dto.Command;
import chess.dto.GameDto;
import chess.dto.LoadCommand;
import chess.dto.MovementDto;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.view.GameState.*;

public final class Controller {
    private long gameId;
    private final GameDao gameDao;
    private final MovementDao movementDao;

    public Controller(final GameDao gameDao, final MovementDao movementDao) {
        this.gameDao = gameDao;
        this.movementDao = movementDao;
    }

    public void run() {
        OutputView.printStartMessage();
        gameId = gameDao.save(false, WHITE);
        State state = new Start(gameId, Chess.create(Board.create(), Points.create()));

        while (state.isNotEnd()) {
            state = nextState(state);
            OutputView.printChessBoard(state.getChess());
        }
        deleteGameIfEmptyMovements();
    }

    private void deleteGameIfEmptyMovements() {
        final List<MovementDto> findMovements = movementDao.findAllBy(gameId);
        if (findMovements.isEmpty()) {
            gameDao.deleteBy(gameId);
        }
    }

    private State nextState(final State state) {
        try {
            return processChess(state);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            nextState(state);
        }
        return state;
    }

    private State processChess(State state) {
        final Command command = InputView.inputGameState();
        state = start(state, command);
        state = move(state, command);
        state = end(state, command);
        state = status(state, command);
        state = loadList(state, command);
        state = load(state, command);
        return state;
    }

    private State start(final State state, final Command command) {
        if (command.getGameState() == START) {
            deleteGameIfEmptyMovements();
            gameId = gameDao.save(false, WHITE);
            return state.start(gameId);
        }
        return state;
    }

    private State move(final State state, final Command command) {
        if (command.getGameState() == MOVE) {
            movementDao.save(command, state.getCurrentPlayer(), gameId);
            return state.move(command);
        }
        return state;
    }

    private State end(final State state, final Command command) {
        if (command.getGameState() == END) {
            announceCurrentChessState(state);
            deleteGameIfEmptyMovements();
            return state.end();
        }
        return state;
    }

    private void announceCurrentChessState(final State state) {
        final Point blackPoint = state.getPointBy(BLACK);
        final Point whitePoint = state.getPointBy(WHITE);
        final Color winner = state.getWinner();
        OutputView.printChessStatus(blackPoint, whitePoint, winner);
    }

    private State status(final State state, final Command command) {
        if (command.getGameState() == STATUS) {
            announceCurrentChessState(state);
            return state.status();
        }
        return state;
    }

    private State loadList(final State state, final Command command) {
        if (command.getGameState() == LOAD_LIST) {
            final List<GameDto> findGames = gameDao.findLatestGamesWithoutBy(gameId);
            OutputView.printFindAllGames(findGames);
            return state.loadList();
        }
        return state;
    }

    private State load(final State state, final Command command) {
        if (command.getGameState() == LOAD_LIST) {
            final LoadCommand loadCommand = InputView.inputLoadCommand();
            return loadGameIfExists(state, loadCommand);
        }
        return state;
    }

    private State loadGameIfExists(final State state, final LoadCommand loadCommand) {
        if (loadCommand.getGameState() == LOAD) {
            deleteGameIfEmptyMovements();
            gameId = loadCommand.getGameId();
            final List<MovementDto> findMovements = movementDao.findAllBy(gameId);
            return state.load(findMovements);
        }
        return state;
    }
}
