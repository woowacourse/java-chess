package database;

import java.sql.Connection;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao(Connection connection) {
        this.connection = connection;
    }
}
