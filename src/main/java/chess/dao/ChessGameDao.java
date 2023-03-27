package chess.dao;

import chess.domain.Board;
import chess.game.GameId;
import chess.game.state.GameState;

public interface ChessGameDao {
    Board findBoard(GameId gameId);

    GameState findGameState(GameId gameId);

    void saveChessGame(GameId gameId, Board board, GameState gameState);

    void createChessGame(GameId gameId, Board board, GameState gameState);

    boolean isExistGame(GameId gameId);

    void deleteGame(GameId gameId);

    void transaction(Runnable runnable);
}
