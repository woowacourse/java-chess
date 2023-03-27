package chess.dao;

import chess.domain.game.ChessGame;

public class InMemoryChessGameDao implements ChessGameDao {
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
    public void init(ChessGame chessGame) {
        this.chessGame = null;
    }
}
