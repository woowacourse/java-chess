package chess.domain.dao;

import chess.db.MySQLConnector;
import chess.domain.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HistoryDaoTest {
    private HistoryDao historyDao;
    private HistoryDatabase history;

    @BeforeEach
    public void setup() throws SQLException {
        historyDao = new HistoryDao();
        history = new HistoryDatabase();
    }

    @Test
    public void connection() {
        Connection con = MySQLConnector.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addUser() throws Exception {
        CommandDto commandDto = new CommandDto("start");
        history.insert(commandDto);
        historyDao.insert(commandDto);
    }
}

