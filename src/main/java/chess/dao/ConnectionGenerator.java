package chess.dao;

import java.sql.Connection;

public interface ConnectionGenerator {
    Connection getConnection();
}
