package chess.controller;

import chess.db.GameRepository;
import chess.domain.board.piece.Color;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.dto.BoardViewDto;
import chess.model.GameModel;

public class WebController {

    private final GameRepository gameRepository = new GameRepository();

    public GameModel initGame() {
        Game game = new NewGame().init();
        int gameId = gameRepository.add(game);

        Color currentTurn = game.getCurrentTurnColor();
        BoardViewDto board = game.boardView();

        return new GameModel(gameId, currentTurn, board);
    }
}
