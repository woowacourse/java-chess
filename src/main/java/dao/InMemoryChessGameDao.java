package dao;

import chess.ChessGame;

public final class InMemoryChessGameDao implements ChessGameDao {
    private ChessGame chessGame;

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
}
