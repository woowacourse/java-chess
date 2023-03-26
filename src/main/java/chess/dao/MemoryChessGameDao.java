package chess.dao;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.game.state.GameState;
import chess.game.state.running.WhiteTurnState;

public class MemoryChessGameDao implements ChessGameDao {
    private GameState gameState = WhiteTurnState.STATE;
    private Board board = new Board(BoardFactory.create());

    @Override
    public Board findBoard(String gameId) {
        return board;
    }

    @Override
    public GameState findGameState(String gameId) {
        return gameState;
    }

    @Override
    public void saveChessGame(String gameId, Board board, GameState gameState) {
        this.board = board;
        this.gameState = gameState;
    }

    @Override
    public void createChessGame(String gameId, Board board, GameState gameState) {
        this.board = board;
        this.gameState = gameState;
    }

    @Override
    public boolean isExistGame(String gameId) {
        return false;
    }

    @Override
    public void transaction(Runnable runnable) {
        runnable.run();
    }
}
