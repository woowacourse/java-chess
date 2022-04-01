package chess.controller;

import chess.db.GameRepository;
import chess.domain.board.piece.Color;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.BoardViewDto;
import chess.model.GameModel;
import chess.model.NewGameModel;

public class WebController {

    private final GameRepository gameRepository = new GameRepository();

    public NewGameModel initGame() {
        Game game = new NewGame().init();
        return new NewGameModel(gameRepository.add(game));
    }

    public GameModel findGame(int gameId) {
        Game game = gameRepository.findById(gameId);

        Color currentTurn = game.getCurrentTurnColor();
        BoardViewDto board = game.boardView();

        return new GameModel(gameId, currentTurn, board);
    }
}
