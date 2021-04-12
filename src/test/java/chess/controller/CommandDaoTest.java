package chess.controller;

import chess.controller.web.dao.CommandDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandDaoTest {
    private CommandDao commandDAO;

    @BeforeEach
    public void setup() throws SQLException {
        commandDAO = new CommandDao();
        commandDAO.deleteAll();
    }

    @Test
    public void connection() {
        Connection con = commandDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    void insert() throws SQLException {
        commandDAO.insert("move a7 a6");
    }
}