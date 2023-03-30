package chess.controller;

import chess.dao.GameDao;
import chess.dao.MovementDao;
import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.command.*;
import chess.domain.point.Point;
import chess.domain.point.Points;
import chess.dto.Command;
import chess.dto.GameDto;
import chess.dto.MovementDto;
import chess.view.GameState;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.view.GameState.*;

public class Controller {
    private final GameDao gameDao;
    private final MovementDao movementDao;
    private final Map<GameState, BiFunction<Active, Command, Active>> actives = new EnumMap<>(GameState.class);

    public Controller(final GameDao gameDao, final MovementDao movementDao) {
        this.gameDao = gameDao;
        this.movementDao = movementDao;
        saveCommands();
    }

    private void saveCommands() {
        actives.put(START, this::start);
        actives.put(MOVE, this::move);
        actives.put(END, this::end);
        actives.put(STATUS, this::status);
        actives.put(LOAD_LIST, this::loadList);
    }

    public void run() {
        OutputView.printStartMessage();
        Active active = Ready.create();
        do {
            active = process(active);
        } while (active.isNotEnd());
        deleteGameHistoryIfNotPlayed(active);
    }

    private Active process(final Active active) {
        Active newActive;
        try {
            final Command command = InputView.inputGameState();
            newActive = actives.get(command.getGameState()).apply(active, command);
            OutputView.printChessBoard(newActive.getChess().getBoardValue());
        } catch (IllegalArgumentException | InputMismatchException | UnsupportedOperationException e) {
            System.out.println(e.getMessage());
            newActive = process(active);
        }
        return newActive;
    }

    private void deleteGameHistoryIfNotPlayed(final Active active) {
        final List<MovementDto> findMovements = movementDao.findAllBy(active.getGameId());
        if (findMovements.isEmpty()) {
            gameDao.deleteBy(active.getGameId());
        }
    }

    private Active start(final Active active, final Command command) {
        final Chess newChess = Chess.create(Board.create(), Points.create());
        final long newGameId = gameDao.save(false, WHITE);
        return new Start(newGameId, newChess).execute(command);
    }

    private Active move(final Active active, final Command command) {
        Active afterActive = active.execute(command);
        movementDao.save(command, active.getCurrentPlayer(), active.getGameId());
        return afterActive;
    }

    private Active end(final Active active, final Command command) {
        deleteGameHistoryIfNotPlayed(active);
        return End.create(active.getGameId());
    }

    private Active status(final Active active, final Command command) {
        final Point blackPoint = active.getPointBy(BLACK);
        final Point whitePoint = active.getPointBy(WHITE);
        final Color winner = active.getWinner();
        OutputView.printChessStatus(blackPoint, whitePoint, winner);
        return new Status(active.getGameId(), active.getChess(), active.getCurrentPlayer());
    }

    private Active loadList(final Active active, final Command command) {
        final List<GameDto> findGames = gameDao.findLatestGamesWithoutBy(active.getGameId());
        OutputView.printFindAllGames(findGames);

        final long gameId = InputView.inputGameId();
        final GameDto gameDto = gameDao.findBy(gameId)
                .orElseThrow(() -> new IllegalArgumentException("불러올 수 없는 게임 번호입니다."));
        final Chess loadChessBy = loadChessBy(gameDto);

        deleteGameHistoryIfNotPlayed(active);
        return new Load(gameDto.getGameId(), loadChessBy, gameDto.getLastPlayer());
    }

    private Chess loadChessBy(final GameDto gameDto) {
        final Chess newChess = Chess.create(Board.create(), Points.create());
        movementDao.findAllBy(gameDto.getGameId())
                .stream()
                .filter(movement -> movement.getPlayer().isNotEmpty())
                .forEach(movement -> newChess.move(movement.getSource(), movement.getTarget(), movement.getPlayer()));
        return newChess;
    }
}
