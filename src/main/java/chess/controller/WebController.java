package chess.controller;

import chess.db.GameRepository;
import chess.domain.game.Game;
import chess.domain.game.GameState;
import chess.domain.game.NewGame;
import chess.dto.request.MoveCommandDto;
import chess.dto.request.PlayGameRequestDto;
import chess.dto.response.CreateGameDto;
import chess.dto.response.SearchResultDto;
import chess.model.FullGameModel;
import chess.model.FullResultModel;
import chess.model.GameCountModel;

public class WebController {

    private final GameRepository gameRepository = new GameRepository();

    public GameCountModel countGames() {
        int totalCount = gameRepository.countAll();
        int overCount = gameRepository.countByState(GameState.OVER);
        int runningCount = totalCount - overCount;

        return new GameCountModel(totalCount, runningCount);
    }

    public CreateGameDto initGame() {
        Game game = new NewGame().init();
        return new CreateGameDto(gameRepository.add(game));
    }

    public SearchResultDto searchGame(int gameId) {
        return new SearchResultDto(gameId, gameRepository.checkById(gameId));
    }

    public FullGameModel findGame(int gameId) {
        Game game = gameRepository.findById(gameId);
        return new FullGameModel(gameId, game);
    }

    public FullGameModel playGame(PlayGameRequestDto request) {
        int gameId = request.getGameId();
        MoveCommandDto moveCommand = request.getMoveCommand();

        Game game = gameRepository.findById(gameId);
        game = game.moveChessmen(moveCommand);
        gameRepository.update(gameId, game);

        return findGame(gameId);
    }

    public FullResultModel findGameResult(int gameId) {
        Game game = gameRepository.findById(gameId);
        if (!game.isEnd()) {
            throw new IllegalArgumentException("아직 게임 결과가 산출되지 않았습니다.");
        }
        return new FullResultModel(gameId, game);
    }
}
