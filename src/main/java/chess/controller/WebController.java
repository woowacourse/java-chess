package chess.controller;

import chess.dao.EventDao;
import chess.dao.GameDao;
import chess.dao.GameState;
import chess.domain.event.Event;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.request.MoveCommandDto;
import chess.dto.request.PlayGameRequestDto;
import chess.dto.response.CreateGameDto;
import chess.dto.response.GameCountDto;
import chess.dto.response.GameDto;
import chess.dto.response.GameResultDto;
import chess.dto.response.SearchResultDto;
import java.util.List;

public class WebController {

    private static final String GAME_NOT_OVER_EXCEPTION_MESSAGE = "아직 게임 결과가 산출되지 않았습니다.";

    private static final String GAME_TABLE_NAME = "game";
    private static final String EVENT_TABLE_NAME = "event";

    private final GameDao gameDao = new GameDao(GAME_TABLE_NAME);
    private final EventDao eventDao = new EventDao(EVENT_TABLE_NAME);

    private Game currentSnapShotOf(int gameId) {
        List<Event> events = eventDao.findAllByGameId(gameId);
        Game game = new NewGame().init();
        for (Event event : events) {
            game = game.moveChessmen(event.toMoveCommand());
        }
        return game;
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
        return new GameDto(gameId, game);
    }

    public GameDto playGame(PlayGameRequestDto request) {
        int gameId = request.getGameId();
        MoveCommandDto moveCommand = request.getMoveCommand();

        Game game = currentSnapShotOf(gameId);
        game = game.moveChessmen(moveCommand);

        eventDao.saveMove(gameId, moveCommand);
        updateGameState(gameId, game);
        return new GameDto(gameId, game);
    }

    private void updateGameState(int gameId, Game game) {
        if (game.isEnd()) {
            gameDao.finishGame(gameId, game);
        }
    }

    public GameResultDto findGameResult(int gameId) {
        Game game = currentSnapShotOf(gameId);
        validateGameOver(game);
        return new GameResultDto(gameId, game);
    }

    private void validateGameOver(Game game) {
        if (!game.isEnd()) {
            throw new IllegalArgumentException(GAME_NOT_OVER_EXCEPTION_MESSAGE);
        }
    }
}
