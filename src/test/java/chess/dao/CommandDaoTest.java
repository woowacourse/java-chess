package chess.dao;

import chess.view.Square;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommandDaoTest {

    @Test
    void connection() {
        final CommandDao commandDao = new CommandDao();
        final Connection connection = commandDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final CommandDao commandDao = new CommandDao();
        commandDao.save("start");
    }

    @Test
    void init() {
        final CommandDao commandDao = new CommandDao();
        commandDao.init();
        final List<String> commands = commandDao.findAll();
        assertThat(commands).isEmpty();
    }

    @Test
    void findAll() {
        final CommandDao commandDao = new CommandDao();
        final List<String> commands = commandDao.findAll();
        assertThat(commands).isNotEmpty();
    }
}