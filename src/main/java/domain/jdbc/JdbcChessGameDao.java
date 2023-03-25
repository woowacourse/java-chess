package domain.jdbc;

import domain.ChessGame;

public interface JdbcChessGameDao {

    String save(ChessGame chessGame);

    ChessGame select(String id);

    void update(String id, ChessGame chessGame);

    void delete(String id);
}
