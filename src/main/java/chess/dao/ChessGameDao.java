package chess.dao;

import chess.domain.Board;
import chess.game.GameId;
import chess.game.GameResult;
import chess.game.state.GameState;
import java.util.List;

public interface ChessGameDao {
    Board findBoard(GameId gameId);

    GameState findGameState(GameId gameId);

    void saveChessGame(GameId gameId, Board board, GameState gameState);

    void createChessGame(GameId gameId, Board board, GameState gameState);

    boolean isExistGame(GameId gameId);

    void deleteGame(GameId gameId);

    List<GameId> findAllGameId();

    void transaction(Runnable runnable);

    void saveGameResult(String name, double score, GameResult gameResult);
}
