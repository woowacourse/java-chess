package chess.dao;

import chess.domain.Board;
import chess.game.GameId;
import chess.game.GameResult;
import chess.game.state.GameState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryChessGameDao implements ChessGameDao {
    private final Map<GameId, ChessGameEntry> chessTable = new HashMap<>();
    private final Map<Integer, Result> gameResultTable = new HashMap<>();
    private int gameResultPk = 1;

    @Override
    public Board findBoard(GameId gameId) {
        return chessTable.get(gameId).board;
    }

    @Override
    public GameState findGameState(GameId gameId) {
        return chessTable.get(gameId).gameState;
    }

    @Override
    public void saveChessGame(GameId gameId, Board board, GameState gameState) {
        ChessGameEntry chessGameEntry = chessTable.get(gameId);
        chessGameEntry.board = board;
        chessGameEntry.gameState = gameState;
    }

    @Override
    public void createChessGame(GameId gameId, Board board, GameState gameState) {
        chessTable.put(gameId, new ChessGameEntry(board, gameState));
    }

    @Override
    public boolean isExistGame(GameId gameId) {
        return chessTable.containsKey(gameId);
    }

    @Override
    public void deleteGame(GameId gameId) {
        chessTable.remove(gameId);
    }

    @Override
    public List<GameId> findAllGameId() {
        return new ArrayList<>(chessTable.keySet());
    }

    @Override
    public void transaction(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void saveGameResult(String name, double score, GameResult gameResult) {
        gameResultTable.put(gameResultPk++, new Result(name, score, gameResult));
    }

    private static class ChessGameEntry {
        private Board board;
        private GameState gameState;

        public ChessGameEntry(Board board, GameState gameState) {
            this.board = board;
            this.gameState = gameState;
        }
    }

    private static class Result {
        private final String name;
        private final double score;
        private final GameResult gameResult;

        public Result(String name, double score, GameResult gameResult) {
            this.name = name;
            this.score = score;
            this.gameResult = gameResult;
        }
    }
}
