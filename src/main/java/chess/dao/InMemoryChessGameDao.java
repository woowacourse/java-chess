package chess.dao;

import chess.domain.game.ChessGame;

public class InMemoryChessGameDao implements ChessGameDao {

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
