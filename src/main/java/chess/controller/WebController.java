package chess.controller;

import chess.db.GameRepository;
import chess.domain.board.piece.Color;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.MoveCommandDto;
import chess.dto.PlayGameRequestDto;
import chess.dto.SearchResultDto;
import chess.dto.response.WebBoardViewDto;
import chess.model.GameModel;
import chess.model.NewGameModel;

public class WebController {

    private final GameRepository gameRepository = new GameRepository();

    public NewGameModel initGame() {
        Game game = new NewGame().init();
        return new NewGameModel(gameRepository.add(game));
    }

    public SearchResultDto searchGame(int gameId) {
        return new SearchResultDto(gameId, gameRepository.checkById(gameId));
    }

    public GameModel findGame(int gameId) {
        Game game = gameRepository.findById(gameId);

        Color currentTurn = game.getCurrentTurnColor();
        WebBoardViewDto board = game.boardWebView();

        return new GameModel(gameId, currentTurn, board);
    }

    public GameModel playGame(PlayGameRequestDto request) {
        int gameId = request.getGameId();
        MoveCommandDto moveCommand = request.getMoveCommand();

        Game game = gameRepository.findById(gameId);
        game = game.moveChessmen(moveCommand);
        gameRepository.update(gameId, game);

        return findGame(gameId);
    }
}
