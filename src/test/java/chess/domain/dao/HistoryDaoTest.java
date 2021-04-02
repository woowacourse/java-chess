package chess.domain.dao;

import chess.domain.dto.HistoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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
        final int id = historyDao.findIdByName("minjeong");
        System.out.println(id);
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

    @Test
    void selectById() throws SQLException {
        final HistoryDto history = historyDao.findById("100");
        assertThat(history).isEqualTo(null);
    }

    @Test
    void clearById() throws SQLException {
        final HistoryDto history = historyDao.findById("8");
        assertThat(history).isEqualTo(null);
    }
}