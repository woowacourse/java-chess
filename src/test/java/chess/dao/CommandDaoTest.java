package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static chess.dao.DBConnection.getConnection;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandDaoTest {
    private CommandDao commandDAO;

    @BeforeEach
    public void setup() {
        commandDAO = new CommandDao();
        commandDAO.deleteAll();
    }

    @Test
    public void connection() {
        Connection con = getConnection();
        assertNotNull(con);
    }

    @Test
    void insert() {
        commandDAO.insert(1L, "a7", "a6");
    }
}