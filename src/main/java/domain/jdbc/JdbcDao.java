package domain.jdbc;

import domain.ChessGame;

public interface JdbcDao {

    void save();

    ChessGame select();

    void update();

    void delete();
}
