package chess.dao;

import chess.domain.ChessGame;

public final class InMemoryChessGameDao implements ChessGameDao {

    private ChessGame chessGame;

    @Override
    public void save(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame select() {
        return chessGame;
    }

    @Override
    public void update(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void delete() {
        this.chessGame = null;
    }
}
