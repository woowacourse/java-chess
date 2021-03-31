package chess.domain.dao;

import chess.dao.ConnectionSetup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Fixture {
    public static void truncateDB() throws SQLException {
        ConnectionSetup con = new ConnectionSetup();
        try (Connection connection = con.getConnection()) {
            executeUpdate(connection, "TRUNCATE TABLE grid");
            executeUpdate(connection, "TRUNCATE TABLE room");
            executeUpdate(connection, "TRUNCATE TABLE piece");
        }
    }

    private static void executeUpdate(Connection connection, String query) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }
}

