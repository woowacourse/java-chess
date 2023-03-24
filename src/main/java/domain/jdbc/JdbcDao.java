package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;

public interface JdbcDao {

    int save(ChessGame chessGame);

    ChessGame selectNewGame(ChessGame chessGame);

    ChessGame select();

    void update();

    void delete();
}
