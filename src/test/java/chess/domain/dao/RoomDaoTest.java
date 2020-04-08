package chess.domain.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomDaoTest {
	private RoomDao roomDao;

	@BeforeEach
	void setUp() {
		roomDao = new RoomDao();
	}

	@Test
	void findByRoomName() throws SQLException {
		String actualBoard = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		Optional<String> expectedBoard = roomDao.findByRoomName("A");

		assertThat(actualBoard).isEqualTo(expectedBoard.orElseThrow(NoSuchElementException::new));
	}

	@Test
	void addRoom() throws SQLException {
		String roomName = "A";
		String actualBoard = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String turn = "black";
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
		String roomName = "A";
		String board = "RNBQKBNR........PPPPPPPP................pppppppp........rnbqkbnr";

		roomDao.updateBoard(roomName, board);
	}
}
