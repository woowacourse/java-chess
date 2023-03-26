package chess.dao;

import chess.domain.ChessGame;

import java.util.List;

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
    public List<Integer> selectAllId() {
        return List.of(1);
    }

    @Override
    public void update(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void delete(final int id) {
        this.chessGame = null;
    }
}
