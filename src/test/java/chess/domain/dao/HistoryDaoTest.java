package chess.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    void name() throws SQLException{
        final int id = historyDao.findIdByName("minjeong");
        System.out.println(id);
    }

    @Test
    void delete() throws SQLException{
        historyDao.delete("minjeong");
    }

    @Test
    void selectAll() throws SQLException{
        historyDao.insert("minjeong");
        historyDao.insert("joanne");
        final List<String> names = historyDao.selectAll();
        assertThat(names).contains("minjeong", "joanne");
    }
}