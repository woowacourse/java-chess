package chess.domain.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomDaoTest {
	private RoomDao roomDao;

	@BeforeEach
	void setUp() throws SQLException {
		roomDao = new RoomDao();
		String board = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String turn = "WHITE";
		String finishFlag = "N";
		roomDao.addRoom("A", board, turn, finishFlag);
		roomDao.addRoom("B", board, turn, finishFlag);
	}

	@Test
	void findByRoomName() throws SQLException {
		String actualBoard = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String expectedBoard = roomDao.findByRoomName("A", "board")
				.orElseThrow(NoSuchElementException::new);

		assertThat(actualBoard).isEqualTo(expectedBoard);
	}

	@Test
	void addRoom() throws SQLException {
		String roomName = "C";
		String actualBoard = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String turn = "BLACK";
		String finishFlag = "N";

		int newRoomId = roomDao.addRoom(roomName, actualBoard, turn, finishFlag);
	}

	@Test
	void deleteRoom() throws SQLException {
		String roomName = "A";

		roomDao.deleteRoom(roomName);
	}

	@Test
	void updateRoom() throws SQLException {
		String roomName = "B";
		String board = "RNBQKBNR................................................rnbqkbnr";
		String turn = "BLACK";
		String finishFlag = "Y";

		roomDao.updateRoom(roomName, board, turn, finishFlag);
	}
}
