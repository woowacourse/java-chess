package chess.dao;

import chess.domain.Board;
import chess.game.state.GameState;

public interface ChessGameDao {
    Board findBoard(String gameId);

    GameState findGameState(String gameId);

    void saveChessGame(String gameId, Board board, GameState gameState);

    void createChessGame(String gameId, Board board, GameState gameState);

    boolean isExistGame(String gameId);

    void transaction(Runnable runnable);
}
