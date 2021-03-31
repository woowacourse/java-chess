package chess.domain.dao;

import chess.domain.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CommandDaoTest {
    private CommandDao commandDao;
    private CommandDatabase history;

    @BeforeEach
    public void setup() throws SQLException {
        commandDao = new CommandDao();
        history = new CommandDatabase();
    }

    @Test
    public void addUser() throws Exception {
        CommandDto commandDto = new CommandDto("start");
        history.insert(commandDto);
        commandDao.insert(commandDto, "1");
    }
}

