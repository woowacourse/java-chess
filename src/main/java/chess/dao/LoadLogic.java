package chess.dao;

import chess.game.GameId;

public interface LoadLogic<T> {
    T load(GameId gameId);
}
