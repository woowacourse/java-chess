package domain.jdbc;

import domain.ChessGame;

public interface JdbcDao {

    String save(ChessGame chessGame);

    ChessGame selectNewGame(ChessGame chessGame);

    ChessGame select(String id);

    void update();

    void delete();
}
