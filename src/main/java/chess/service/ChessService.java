package chess.service;

import chess.dao.EventDao;
import chess.dao.EventDaoImpl;
import chess.dao.GameDao;
import chess.dao.GameDaoImpl;
import chess.dao.GameState;
import chess.domain.event.Event;
import chess.domain.event.InitEvent;
import chess.domain.event.MoveCommand;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.CreateGameDto;
import chess.dto.GameCountDto;
import chess.dto.GameDto;
import chess.dto.GameResultDto;
import chess.dto.SearchResultDto;
import java.util.List;

public class ChessService {

    private static final String GAME_NOT_CREATED_EXCEPTION_MESSAGE = "아직 생성되지 않은 게임입니다.";
    private static final String GAME_NOT_OVER_EXCEPTION_MESSAGE = "아직 게임 결과가 산출되지 않았습니다.";

    private final static ChessService instance = new ChessService(GameDaoImpl.ofProd(), EventDaoImpl.ofProd());

    private final GameDao gameDao;
    private final EventDao eventDao;

    ChessService(GameDao gameDao, EventDao eventDao) {
        this.gameDao = gameDao;
        this.eventDao = eventDao;
    }

    public static ChessService getInstance() {
        return instance;
    }

    public GameCountDto countGames() {
        int totalCount = gameDao.countAll();
        int runningCount = gameDao.countByState(GameState.RUNNING);

        return new GameCountDto(totalCount, runningCount);
    }

    public CreateGameDto initGame() {
        int gameId = gameDao.saveAndGetGeneratedId();
        return new CreateGameDto(gameId);
    }

    public SearchResultDto searchGame(int gameId) {
        return new SearchResultDto(gameId, gameDao.checkById(gameId));
    }

    public GameDto findGame(int gameId) {
        Game game = currentSnapShotOf(gameId);
        return game.toDtoOf(gameId);
    }

    public GameDto playGame(int gameId, MoveCommand moveCommand) {
        Game game = currentSnapShotOf(gameId).moveChessmen(moveCommand);

        eventDao.saveMove(gameId, moveCommand);
        updateGameState(gameId, game);
        return game.toDtoOf(gameId);
    }

    private void updateGameState(int gameId, Game game) {
        if (game.isEnd()) {
            gameDao.finishGame(gameId);
        }
    }

    public GameResultDto findGameResult(int gameId) {
        Game game = currentSnapShotOf(gameId);
        validateGameOver(game);
        return new GameResultDto(gameId, game);
    }

    private Game currentSnapShotOf(int gameId) {
        validateGameExistence(gameId);
        List<Event> events = eventDao.findAllByGameId(gameId);
        Game game = new NewGame().play(new InitEvent());
        for (Event event : events) {
            game = game.moveChessmen(event.toMoveCommand());
        }
        return game;
    }

    // TODO: implement INIT event for checking initialization
    private void validateGameExistence(int gameId) {
        if (!gameDao.checkById(gameId)) {
            throw new IllegalArgumentException(GAME_NOT_CREATED_EXCEPTION_MESSAGE);
        }
    }

    private void validateGameOver(Game game) {
        if (!game.isEnd()) {
            throw new IllegalArgumentException(GAME_NOT_OVER_EXCEPTION_MESSAGE);
        }
    }
}
