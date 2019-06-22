package chess.dao;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.domain.Piece;
import chess.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomDaoTest {
	private RoomDao roomDao;

	@BeforeEach
	public void setUp() throws Exception {
		DbConnector dbConnector = new DbConnector(DataSource.getInstance());
		roomDao = RoomDao.from(dbConnector);
		new TableCreator(dbConnector).create();
		roomDao.deleteAll();
	}

	@Test
	public void addTest() {
		assertDoesNotThrow(() -> roomDao.add());
	}

	@Test
	public void findByRoomIdTest() {
		final long id = 1L;
		RoomDto expected = new RoomDto();
		expected.setId(id);
		expected.setStatus(false);
		expected.setWinner(null);

		roomDao.add();
		RoomDto actual = roomDao.findById(id).get();

		assertEquals(expected, actual);
	}

	@Test
	public void findByStatusTest() {
		final boolean status = false;

		List<RoomDto> expected = new ArrayList<>();
		RoomDto roomDto1 = new RoomDto();
		roomDto1.setId(1L);
		roomDto1.setStatus(false);
		roomDto1.setWinner(null);

		RoomDto roomDto2 = new RoomDto();
		roomDto2.setId(2L);
		roomDto2.setStatus(false);
		roomDto2.setWinner(null);

		expected.add(roomDto1);
		expected.add(roomDto2);

		roomDao.add();
		roomDao.add();
		List<RoomDto> actual = roomDao.findAllByStatus(status);

		assertEquals(expected, actual);
	}

	@Test
	public void updateStatusTest() {
		final long id = 1L;
		final String winner = Piece.Color.WHITE.getName();

		roomDao.add();
		roomDao.updateStatus(id, winner);
		RoomDto actual = roomDao.findById(id).get();

		RoomDto expected = new RoomDto();
		expected.setId(id);
		expected.setStatus(true);
		expected.setWinner(winner);

		assertEquals(expected, actual);
	}

	@Test
	public void getLatestTest() {
		roomDao.add();
		roomDao.add();

		long expected = 2L;
		long actual = roomDao.getLatestId().get();

		assertEquals(expected, actual);
	}

}
