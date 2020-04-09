package dao;

import dao.exceptions.DaoNoneSelectedException;
import dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoomDaoTest {
	private RoomDao roomDao;
	private static final String roomName = "그니";

	@BeforeEach
	void setUp() {
		roomDao = new RoomDao();
	}

	@Test
	void getConnection() {
		Connection connection = roomDao.getConnection();
		assertThat(connection).isNotNull();
	}

	@Test
	void closeConnection() {
		Connection connection = roomDao.getConnection();
		roomDao.closeConnection(connection);
	}

	@Test
	void addRoomByRoomName() throws SQLException {
		final int resultNum = roomDao.addRoomByRoomName(roomName);
		assertThat(resultNum).isEqualTo(1);
		assertThat(roomDao.findRoomByRoomName(roomName).getRoomName()).isEqualTo(roomName);

		roomDao.deleteRoomByRoomName(roomName);
	}

	@Test
	void findRoomByRoomName() throws SQLException {
		roomDao.addRoomByRoomName(roomName);

		final RoomDto roomDto = roomDao.findRoomByRoomName(roomName);
		assertThat(roomDto.getRoomName()).isEqualTo(roomName);

		roomDao.deleteRoomByRoomName(roomName);
	}

	@Test
	void deleteRoomByRoomName() throws SQLException {
		roomDao.addRoomByRoomName(roomName);

		final int resultNum = roomDao.deleteRoomByRoomName(roomName);
		assertThat(resultNum).isEqualTo(1);
		assertThatThrownBy(() -> roomDao.findRoomByRoomName(roomName))
				.isInstanceOf(DaoNoneSelectedException.class);
	}
}