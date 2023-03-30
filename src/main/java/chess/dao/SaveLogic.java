package chess.dao;

import chess.domain.Board;
import chess.game.GameId;
import chess.game.state.GameState;

public interface SaveLogic {
    void save(GameId gameId, Board board, GameState gameState);
}
