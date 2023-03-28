package dao;

import domain.ChessGame;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);

    void delete(ChessGame chessGame);
}
