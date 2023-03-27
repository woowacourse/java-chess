package database.db;

import domain.ChessBoard;

public interface ChessBoardDao {

    void update(ChessBoard chessBoard);

    ChessBoard read();

    void delete();

}
