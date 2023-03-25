package dao;

import chess.ChessGame;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);

    void reset();
}
