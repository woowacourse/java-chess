package chess.dao;

import chess.domain.ChessGame;

public final class InMemoryChessGameDao implements ChessGameDao {

    private ChessGame chessGame;

    @Override
    public int save(ChessGame chessGame) {
        this.chessGame = chessGame;
        return 1;
    }

    @Override
    public ChessGame select(final int id) {
        return chessGame;
    }

    @Override
    public void update(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void delete(ChessGame chessGame) {
        this.chessGame = null;
    }
}
