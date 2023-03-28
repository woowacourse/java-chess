package dao;

import domain.chessGame.ChessBoard;

public interface ChessBoardDao {
    void save(ChessBoard chessBoard);

    ChessBoard find();

    void update(ChessBoard chessBoard);

    void delete();
}
