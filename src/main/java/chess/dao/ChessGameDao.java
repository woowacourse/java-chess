package chess.dao;

import chess.domain.ChessGame;

public interface ChessGameDao {
    int save(final ChessGame chessGame);

    ChessGame select(final int id);

    void update(final ChessGame chessGame);

    void delete(final ChessGame chessGame);
}
