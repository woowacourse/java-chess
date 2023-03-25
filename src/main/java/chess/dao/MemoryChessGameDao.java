package chess.dao;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.game.state.GameState;
import chess.game.state.running.WhiteTurnState;

public class MemoryChessGameDao implements ChessGameDao {
    private GameState gameState = WhiteTurnState.STATE;
    private Board board = new Board(BoardFactory.create());

    @Override
    public Board findBoard() {
        return board;
    }

    @Override
    public GameState findGameState() {
        return gameState;
    }

    @Override
    public void saveChessGame(Board board, GameState gameState) {
        this.board = board;
        this.gameState = gameState;
    }
}
