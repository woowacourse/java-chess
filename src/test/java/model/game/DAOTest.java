package model.game;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {
    @Test
    void connectionTest() throws SQLException {
        Connection connection = DAO.connect();
        assertNotNull(connection);
        connection.close();
    }
}