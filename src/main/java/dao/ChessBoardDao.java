package dao;

import domain.chessGame.ChessBoard;

public interface ChessBoardDao {
    void save(ChessBoard chessBoard);

    ChessBoard select();

    void update(ChessBoard chessBoard);

    void delete();
}
