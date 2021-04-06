package chess.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HistoryDaoTest {
    private HistoryDao historyDao;

    @BeforeEach
    void setUp() {
        historyDao = new HistoryDao();
    }

    @Test
    void insert() throws SQLException {
        historyDao.insert("minjeong");
    }

    @Test
    void name() throws SQLException {
        final Optional<Integer> id = historyDao.findIdByName("minjeong");
        System.out.println(id.get());
    }

    @Test
    void delete() throws SQLException {
        historyDao.delete("minjeong");
    }

    @Test
    void selectAll() throws SQLException {
        historyDao.insert("minjeong");
        historyDao.insert("joanne");
        final List<String> names = historyDao.selectActive();
        assertThat(names).contains("minjeong", "joanne");
    }
}