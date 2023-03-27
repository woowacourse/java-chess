package chess.dao;

import chess.domain.Board;
import chess.game.GameId;
import chess.game.state.GameState;
import java.util.HashMap;
import java.util.Map;

public class MemoryChessGameDao implements ChessGameDao {
    private static final Map<GameId, ChessGameEntry> CHESS_TABLE = new HashMap<>();

    @Override
    public Board findBoard(GameId gameId) {
        return CHESS_TABLE.get(gameId).board;
    }

    @Override
    public GameState findGameState(GameId gameId) {
        return CHESS_TABLE.get(gameId).gameState;
    }

    @Override
    public void saveChessGame(GameId gameId, Board board, GameState gameState) {
        ChessGameEntry chessGameEntry = CHESS_TABLE.get(gameId);
        chessGameEntry.board = board;
        chessGameEntry.gameState = gameState;
    }

    @Override
    public void createChessGame(GameId gameId, Board board, GameState gameState) {
        CHESS_TABLE.put(gameId, new ChessGameEntry(board, gameState));
    }

    @Override
    public boolean isExistGame(GameId gameId) {
        return CHESS_TABLE.containsKey(gameId);
    }

    @Override
    public void deleteGame(GameId gameId) {
        CHESS_TABLE.remove(gameId);
    }

    @Override
    public void transaction(Runnable runnable) {
        runnable.run();
    }

    private static class ChessGameEntry {
        private Board board;
        private GameState gameState;

        public ChessGameEntry(Board board, GameState gameState) {
            this.board = board;
            this.gameState = gameState;
        }
    }
}
