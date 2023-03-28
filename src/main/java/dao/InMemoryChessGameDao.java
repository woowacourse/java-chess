package dao;

import chess.ChessGame;
import java.util.List;

public final class InMemoryChessGameDao implements ChessGameDao {
    private ChessGame chessGame;

    @Override
    public List<String> gameIds() {
        return null;
    }

    @Override
    public void save(final ChessGame chessGame, final String gameId) {
        this.chessGame = chessGame;
    }

    @Override
    public String createChessStatus(final ChessGame chessGame) {
        return null;
    }

    @Override
    public ChessGame select(final String gameId) {
        return chessGame;
    }

    @Override
    public void update(final ChessGame chessGame, final String gameId) {
        this.chessGame = chessGame;
    }

    @Override
    public void reset(final String gameId) {
        this.chessGame = null;
    }
}
