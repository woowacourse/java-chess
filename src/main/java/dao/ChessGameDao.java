package dao;

import domain.board.ChessGame;

public interface ChessGameDao {

    void insert(final ChessGame chessGame);
    ChessGame read();
    void delete();
}
