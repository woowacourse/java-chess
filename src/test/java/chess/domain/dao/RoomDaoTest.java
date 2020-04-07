package chess.domain.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomDaoTest {
	private RoomDao roomDao;

	@BeforeEach
	void setUp() {
		roomDao = new RoomDao();
	}

	@Test
	void findByRoomId() throws SQLException {
		String board = "RNBQKBNR........PPPPPPPP................pppppppp........rnbqkbnr";
		assertThat(roomDao.findByRoomId("2")).isEqualTo(board);
	}

	@Test
	void addRoom() throws SQLException {
		String board = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		roomDao.addRoom("1번방", board, "black", "N");
	}

	@Test
	void deleteRoom() throws SQLException {
		roomDao.deleteRoom("1??");
	}

	@Test
	void updateRoom() throws SQLException {
		String board = "RNBQKBNR........PPPPPPPP................pppppppp........rnbqkbnr";
		roomDao.updateBoard("1번방", board);
	}
}
