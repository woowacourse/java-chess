package chess.dao;

import chess.DatabaseConnect;

import java.sql.Connection;

abstract class Dao {
    private static DatabaseConnect databaseConnect;
    static Connection connection;

    static {
        databaseConnect = DatabaseConnect.getInstance();
        connection = databaseConnect.getConnection();
    }
}
