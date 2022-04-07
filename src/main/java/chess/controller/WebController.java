package chess.controller;

import chess.dao.EventDao;
import chess.dao.GameDao;
import chess.dao.GameState;
import chess.domain.event.Event;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.domain.event.MoveCommand;
import chess.dto.request.PlayGameRequestDto;
import chess.dto.response.CreateGameDto;
import chess.dto.response.GameCountDto;
import chess.dto.response.GameDto;
import chess.dto.response.GameResultDto;
import chess.dto.response.SearchResultDto;
import java.util.List;

public class WebController {

    private static final String GAME_NOT_OVER_EXCEPTION_MESSAGE = "아직 게임 결과가 산출되지 않았습니다.";

    private final GameDao gameDao = GameDao.ofProd();
    private final EventDao eventDao = EventDao.ofProd();

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
        return game.toDtoOf(gameId);
    }

    public GameDto playGame(PlayGameRequestDto request) {
        int gameId = request.getGameId();
        MoveCommand moveCommand = request.getMoveCommand();

        Game game = currentSnapShotOf(gameId);
        game = game.moveChessmen(moveCommand);

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

    private void validateGameOver(Game game) {
        if (!game.isEnd()) {
            throw new IllegalArgumentException(GAME_NOT_OVER_EXCEPTION_MESSAGE);
        }
    }
}
