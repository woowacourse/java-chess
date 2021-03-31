package chess.domain.dao;

import chess.db.MySQLConnector;
import chess.domain.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommandDaoTest {
    private CommandDao commandDao;
    private CommandDatabase history;

    @BeforeEach
    public void setup() throws SQLException {
        commandDao = new CommandDao();
        history = new CommandDatabase();
    }

    @Test
    public void connection() {
        Connection con = MySQLConnector.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addUser() throws Exception {
        CommandDto commandDto = new CommandDto("start", "1");
        history.insert(commandDto);
        commandDao.insert(commandDto);
    }
}

