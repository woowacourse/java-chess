package chess.controller;

import chess.db.GameRepository;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.MoveCommandDto;
import chess.dto.SearchResultDto;
import chess.dto.response.CreateGameDto;
import chess.dto.response.PlayGameRequestDto;
import chess.model.FullGameModel;

public class WebController {

    private final GameRepository gameRepository = new GameRepository();

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
}
