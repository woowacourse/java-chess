package dao;

import domain.board.ChessGame;

public interface ChessGameDao {

    void create(final ChessGame chessGame);
    ChessGame read();
    void update(final ChessGame chessGame);
    void delete();
}
