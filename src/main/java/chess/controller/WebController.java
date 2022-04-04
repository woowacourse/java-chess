package chess.controller;

import chess.db.GameRepository;
import chess.db.entity.FullGameEntity;
import chess.domain.game.Game;
import chess.domain.game.GameState;
import chess.domain.game.NewGame;
import chess.dto.request.MoveCommandDto;
import chess.dto.request.PlayGameRequestDto;
import chess.dto.response.CreateGameDto;
import chess.dto.response.SearchResultDto;
import chess.dto.response.GameDto;
import chess.dto.response.GameResultDto;
import chess.dto.response.GameCountDto;

public class WebController {

    private static final String GAME_NOT_OVER_EXCEPTION_MESSAGE = "아직 게임 결과가 산출되지 않았습니다.";

    private final GameRepository gameRepository = new GameRepository();

    public GameCountDto countGames() {
        int totalCount = gameRepository.countAll();
        int overCount = gameRepository.countByState(GameState.OVER);
        int runningCount = totalCount - overCount;

        return new GameCountDto(totalCount, runningCount);
    }

    public CreateGameDto initGame() {
        Game game = new NewGame().init();
        return new CreateGameDto(gameRepository.add(game));
    }

    public SearchResultDto searchGame(int gameId) {
        return new SearchResultDto(gameId, gameRepository.checkById(gameId));
    }

    public GameDto findGame(int gameId) {
        Game game = gameRepository.findById(gameId);
        return new GameDto(gameId, game);
    }

    public GameDto playGame(PlayGameRequestDto request) {
        int gameId = request.getGameId();
        MoveCommandDto moveCommand = request.getMoveCommand();
        Game game =  gameRepository.findById(gameId);

        game = game.moveChessmen(moveCommand);

        FullGameEntity gameEntity = game.toEntityOf(gameId);
        gameRepository.update(gameEntity, moveCommand);
        return findGame(gameId);
    }

    public GameResultDto findGameResult(int gameId) {
        Game game = gameRepository.findById(gameId);
        validateGameOver(game);
        return new GameResultDto(gameId, game);
    }

    private void validateGameOver(Game game) {
        if (!game.isEnd()) {
            throw new IllegalArgumentException(GAME_NOT_OVER_EXCEPTION_MESSAGE);
        }
    }
}
