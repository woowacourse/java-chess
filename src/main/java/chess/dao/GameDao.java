package chess.dao;

import java.sql.Connection;

public interface GameDao {
    Connection getConnection();

    void save();

    void deleteById(int id);

    int getId();
}
