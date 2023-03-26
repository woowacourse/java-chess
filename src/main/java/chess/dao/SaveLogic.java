package chess.dao;

import chess.domain.Board;
import chess.game.state.GameState;

public interface SaveLogic {
    void save(String gameId, Board board, GameState gameState);
}
