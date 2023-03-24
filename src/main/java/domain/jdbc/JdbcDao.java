package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;

public interface JdbcDao {

    void save(ChessBoard chessBoard);

    ChessGame select();

    void update();

    void delete();
}
