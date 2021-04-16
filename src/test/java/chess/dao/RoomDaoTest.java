package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class RoomDaoTest {
    private RoomDao roomDao;

    @BeforeEach
    public void setup() {
        roomDao = new RoomDao();
        roomDao.deleteAll();
    }

    @Test
    void insert() {
        roomDao.insert("newName");
    }

    @Test
    void delete() {
        roomDao.delete(1L);
    }
}