package chess.dao;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.domain.Piece;
import chess.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomDaoTest {
    private RoomDao roomDao;

    @BeforeEach
    public void setUp() throws Exception {
        DbConnector dbConnector = new DbConnector(DataSource.getInstance());
        roomDao = RoomDao.from(DataSource.getInstance());
        new TableCreator(dbConnector).create();
        roomDao.deleteAll();
    }

    @Test
    public void addTest() {
        assertDoesNotThrow(() -> roomDao.add());
    }

    @Test
    public void findByRoomIdTest() {
        roomDao.add();
        final long id = roomDao.getLatestId().get();
        RoomDto expected = new RoomDto();
        expected.setId(id);
        expected.setStatus(false);
        expected.setWinner(null);

        RoomDto actual = roomDao.findById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    public void findByStatusTest() {
        final boolean status = false;
        roomDao.add();
        roomDao.add();
        List<RoomDto> actual = roomDao.findAllByStatus(status);

        assertEquals(2, actual.size());
    }

    @Test
    public void updateStatusTest() {
        roomDao.add();
        final long id = roomDao.getLatestId().get();
        final String winner = Piece.Color.WHITE.getName();

        RoomDto expected = new RoomDto();
        expected.setId(id);
        expected.setStatus(true);
        expected.setWinner(winner);

        roomDao.updateStatus(id, winner);
        RoomDto actual = roomDao.findById(id).get();

        assertEquals(expected, actual);
    }

    @Test
    public void getLatestTest() {
        roomDao.add();
        long id = roomDao.getLatestId().get();
        roomDao.add();
        long expected = id + 1;

        long actual = roomDao.getLatestId().get();

        assertEquals(expected, actual);
    }
}
