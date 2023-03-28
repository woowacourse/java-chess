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
    public void save(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame select() {
        return chessGame;
    }

    @Override
    public void update(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void reset() {
        this.chessGame = null;
    }
}
