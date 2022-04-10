package chess.service;

import chess.dao.EventDao;
import chess.dao.GameDao;
import chess.dao.GameState;
import chess.domain.event.Event;
import chess.domain.event.InitEvent;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.CreateGameDto;
import chess.dto.GameCountDto;
import chess.dto.GameDto;
import chess.dto.GameResultDto;
import chess.dto.SearchResultDto;
import java.util.List;

public class ChessService {

    private static final String GAME_NOT_OVER_EXCEPTION_MESSAGE = "아직 게임 결과가 산출되지 않았습니다.";

    private static final ChessService instance = new ChessService(new GameDao(), new EventDao());

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
        eventDao.save(gameId, new InitEvent());
        return new CreateGameDto(gameId);
    }

    public SearchResultDto searchGame(int gameId) {
        return new SearchResultDto(gameId, gameDao.checkById(gameId));
    }

    public GameDto findGame(int gameId) {
        Game game = currentSnapShotOf(gameId);
        return game.toDtoOf(gameId);
    }

    public GameDto playGame(int gameId, Event moveEvent) {
        Game game = currentSnapShotOf(gameId).play(moveEvent);

        eventDao.save(gameId, moveEvent);
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
        List<Event> events = eventDao.findAllByGameId(gameId);
        Game game = new NewGame();
        for (Event event : events) {
            game = game.play(event);
        }
        return game;
    }

    private void validateGameOver(Game game) {
        if (!game.isEnd()) {
            throw new IllegalArgumentException(GAME_NOT_OVER_EXCEPTION_MESSAGE);
        }
    }
}
